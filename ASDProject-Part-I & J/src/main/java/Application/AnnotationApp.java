package Application;


import framework.FWContext;

public class AnnotationApp {


public static void main(String[] args){
      FWContext context = new FWContext();
        context.readServiceClasses();

        IGreetingService greetingService = new GreetingService();
        Person person;
        person=greetingService.addCustomer("Frank Brown", "Doee", "doe@miu.edu",
                "1000N 4th", "Fairfield", "Iowa", "52557");
        context.publishEvent(new NewGreetingEvent(person));
        context.publishEvent(new AddGreetingEvent("New Greeting is added"));
    }
}