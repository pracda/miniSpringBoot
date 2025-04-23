package Application;

import framework.Autowired;
import framework.Qualifier;
import framework.Service;

// Service consumer
@Service
public class GreetingConsumer {
   public GreetingConsumer() {
    }
    public void greet(String message)    {
        System.out.println("GreetingConsumer.greet() "+message);

    }
}