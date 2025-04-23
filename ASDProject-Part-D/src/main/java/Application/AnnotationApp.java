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

            BetterGreeting betterGreeting = context.getBean(BetterGreeting.class);
            betterGreeting.greet();

            FancyGreeting fancyGreeting = context.getBean(FancyGreeting.class);
            fancyGreeting.greet();

            GoodGreeting goodGreeting = context.getBean(GoodGreeting.class);
            goodGreeting.greet();

            SimpleGreeting simpleGreeting = context.getBean(SimpleGreeting.class);
            simpleGreeting.greet();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}