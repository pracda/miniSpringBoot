package framework;

import org.reflections.Reflections;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FWContext {
    private static List<Object> serviceObjectMap = new ArrayList<>();
    List<Object> serviceObjectInstantiated = new ArrayList<>();
    List<Object> listenerObjectMap = new ArrayList<>();

    private static Properties properties;
    private String activeProfile;
    private Timer scheduler;

    private ExecutorService executor;


    public FWContext() {
        loadProperties();
        executor = Executors.newFixedThreadPool(10);
    }

    public void readServiceClasses(){
        try {
            Reflections reflections = new Reflections("Application");
            Set<Class<?>> serviceTypes = reflections.getTypesAnnotatedWith(Service.class);


            for (Class<?> serviceClass : serviceTypes) {
                Profile profileAnnotation = serviceClass.getAnnotation(Profile.class);
                if (profileAnnotation == null || hasMatchingProfile(profileAnnotation.value())) {
                    serviceObjectMap.add(serviceClass.getDeclaredConstructor().newInstance());
                }
            }
            performDI();
            scheduleTasks();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void performDI() {
        try {

            for (Object theClass : serviceObjectMap) {
                Class<?> clazz = theClass.getClass();
                Constructor<?>[] constructors= clazz.getConstructors();
                     for(Constructor<?> constructor:constructors){
                         if(constructor.isAnnotationPresent(Autowired.class)){
                          Class<?>[] parameterTypes=constructor.getParameterTypes();
                          Object[] parameterInstances=new Object[parameterTypes.length];
                          for(int i=0;i<parameterTypes.length;i++){
                              Class<?> parameterType=parameterTypes[i];
                              parameterInstances[i]=getServiceBeanOfType(parameterType);
                          }
                              constructor.setAccessible(true);
                              Object instance=constructor.newInstance(parameterInstances);
                              serviceObjectInstantiated.add(instance);
                              break;
                          }
                     }

                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Autowired.class) && method.getName().startsWith("set")) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        Object[] parameterInstances = new Object[parameterTypes.length];
                        for (int i = 0; i < parameterTypes.length; i++) {
                            Class<?> parameterType = parameterTypes[i];
                            parameterInstances[i] = getServiceBeanOfType(parameterType);
                        }
                        method.setAccessible(true);
                        method.invoke(theClass, parameterInstances);
                        serviceObjectInstantiated.add(theClass);

                    }

                }
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(EventListener.class)) {
                        listenerObjectMap.add(theClass);
                    }
                }


                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Autowired.class)) {
                        Class<?> fieldType = field.getType();
                        Object fieldInstance;
                        if(field.isAnnotationPresent(Qualifier.class)) {
                            String qualifier = field.getAnnotation(Qualifier.class).value();
                             fieldInstance = getServiceBeanOfQualifier(field.getType(), qualifier);

                        }
                        else {
                             fieldInstance = getServiceBeanOfType(fieldType);
                        }
                        field.setAccessible(true);
                        field.set(theClass, fieldInstance);
                        serviceObjectInstantiated.add(theClass);
                    }
                    if (field.isAnnotationPresent(Value.class)) {
                        Value annotation = field.getAnnotation(Value.class);
                        String key = annotation.value();
                        String value = properties.getProperty(key);
                        if (value != null) {
                            field.setAccessible(true);
                            field.set(theClass, convertValueToFieldType(field.getType(), value));
                        }
                    }
                }
            }



        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = FWContext.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
                activeProfile = properties.getProperty("profiles.active");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean hasMatchingProfile(String[] profiles) {
        if (activeProfile != null) {
            for (String profile : profiles) {
                if (profile.equals(activeProfile)) {
                    return true;
                }
            }
        }
        return false;
    }


    private Object convertValueToFieldType(Class<?> fieldType, String value) {
        if (fieldType == String.class) {
            return value;
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            // Add support for other data types as needed
            return null;
        }
    }
    private void scheduleTasks() {
        for (Object theClass : serviceObjectInstantiated) {
            Class<?> clazz = theClass.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Scheduled.class)) {
                    Scheduled scheduledAnnotation = method.getAnnotation(Scheduled.class);
                    long delay = scheduledAnnotation.value();
                    String corn = scheduledAnnotation.cron();

                    if (delay > 0) {
                        scheduler.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    method.setAccessible(true);
                                    method.invoke(theClass);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, delay, delay);
                    } else if (!corn.isEmpty()) {
                        scheduler.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    method.setAccessible(true);
                                    method.invoke(theClass);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, getCronDelay(corn), getCronDelay(corn));
                    }
                }
            }
        }
    }
    private long getCronDelay(String cronExpression) {
        String[] parts = cronExpression.split("\\s+");
        int seconds = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return (minutes * 60 + seconds) * 1000;
    }
    public void publishEvent(Object event) {
        for (Object listener : listenerObjectMap) {
            Class<?> clazz = listener.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            logThreadInfo("Before publisher event");
            for (Method method : methods) {
                if (method.isAnnotationPresent(EventListener.class)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0].isAssignableFrom(event.getClass())) {
                        if (method.isAnnotationPresent(Async.class)) {
                            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
                                    logThreadInfo("Before async publisher event");
                                     invokeMethod(listener, method, event);
                                    logThreadInfo("After async publisher event");}, executor);
                            future.whenComplete((result, throwable) -> {
                                if (throwable != null) {
                                    throwable.printStackTrace();
                                } else {

                                    System.out.println("Async publisher completed.");
                                }
                            });
                        } else {
                            logThreadInfo("Before sync publisher event");
                            invokeMethod(listener, method, event);
                            logThreadInfo("After sync publisher event");
                        }
                    }
                }
            }
        }
    }
    private void logThreadInfo(String message) {
        System.out.println(message + " - Thread ID: " + Thread.currentThread().getId() + ", Name: " + Thread.currentThread().getName());
    }
    private void invokeMethod(Object listener, Method method, Object event) {
        try {
            method.setAccessible(true);
            method.invoke(listener, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            } else {
                e.printStackTrace();
            }
        }
    }
    public Object getServiceBeanOfType(Class interfaceClass) {
        Object service = null;
        try {
            for (Object theClass : serviceObjectMap) {
                Class<?>[] interfaces = theClass.getClass().getInterfaces();
                for (Class<?> theInterface : interfaces) {
                    if (theInterface.getName().contentEquals(interfaceClass.getName()))
                        return  theClass;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }
    public Object getServiceBeanOfQualifier(Class interfaceClass,String qualifier) {
        Object service = null;
        try {
            for (Object theClass : serviceObjectMap) {
                if (interfaceClass.isInstance(theClass)) {
                    Qualifier annotation = theClass.getClass().getAnnotation(Qualifier.class);
                    if (annotation != null && qualifier.equals(annotation.value())) {
                        return theClass;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }
    public <T> T getBean(Class<T> beanClass) {
        for (Object bean : serviceObjectInstantiated) {
            if (beanClass.isInstance(bean)) {
                return beanClass.cast(bean);
            }
        }
        return null;
    }
    public void run(Class<?> applicationClass) {
        try {
            readServiceClasses();
            performDI();

            Object applicationObject = createApplicationInstance(applicationClass);

            if (applicationObject instanceof Runnable) {
                ((Runnable) applicationObject).run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object createApplicationInstance(Class<?> applicationClass) throws Exception {
        Constructor<?> constructor = applicationClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

}
