package Application;

import framework.FWContext;

// Application
public class AnnotationApp {
    public static void main(String[] args) {

        FWContext context = new FWContext();
        context.readServiceClasses();
        BetterGreeting betterGreeting = context.getBean(BetterGreeting.class);
        betterGreeting.greet();
        FancyGreetingService fancyGreetingService = context.getBean(FancyGreetingService.class);
        fancyGreetingService.greet();
        FineGreeting fineGreeting = context.getBean(FineGreeting.class);
        fineGreeting.greet();


    }
}