package Application;

import framework.Qualifier;
import framework.Service;

@Service
@Qualifier("goodGreeting")
public class GoodGreeting implements GreetingService{
public void sayHello(){
System.out.println("Good Morning from GoodGreetings");

}
public void greet(){
    sayHello();
}

}
