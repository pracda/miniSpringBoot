package Application;

import framework.After;
import framework.Around;
import framework.Aspect;
import framework.Before;

@Aspect
public class MyGreetingAspect {
    @Before("execution(* Application.GreetingService.*(..))")
    public void traceBefore() {
        System.out.println("Before executing the method addCustomer");
    }
    @After("execution(* Application.GreetingService.*(..))")
    public void traceAfter() {
        System.out.println("After executing the method addCustomer");
    }
    @Around("execution(* Application.GreetingService.*(..))")
    public void traceAround() {
        System.out.println("Around before the method addCustomer");
        traceBefore();
        System.out.println("Around after the method addCustomer");
    }

}
