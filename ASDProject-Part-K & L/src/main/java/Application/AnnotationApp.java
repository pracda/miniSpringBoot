package Application;


import framework.FWContext;

public class AnnotationApp {


public static void main(String[] args){
      FWContext context = new FWContext();
        context.readServiceClasses();
        GreetingService greetingService = new GreetingService();
        //String name, String surname,String email, String street, String city, String state, String zip
//        context.executeBeforeAndAfterMethods(greetingService.addCustomer("John","Doe", "johndoe@miu.edu",
//                "1000N 4th", "Fairfield", "Iowa", "52557"));
//        //Testing My greeting aspect methods work
//       MyGreetingAspect loggingTest = new MyGreetingAspect();
//        loggingTest.traceBefore();
//        loggingTest.traceAfter();
//        loggingTest.traceAround();
    context.performDI(); // execute DI and detect @Async methods
    greetingService.addCustomerAsync("John","Doe", "johndoe@miu.edu",
                "1000N 4th", "Fairfield", "Iowa", "52557");
    System.out.println("Main thread continues...");
    }
}