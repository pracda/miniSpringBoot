package Application;


import framework.FWContext;

public class AnnotationApp implements Runnable {


public static void main(String[] args){
      AnnotationApp app = new AnnotationApp();
        app.run();
    }
    @Override
    public void run() {
        try {
            FWContext context = new FWContext();
            context.readServiceClasses();

            FancyGreeting fancyGreeting = context.getBean(FancyGreeting.class);
            System.out.println("fancyGreeting = " + fancyGreeting);
            fancyGreeting.greet();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}