package Application;

import framework.Autowired;
import framework.Qualifier;
import framework.Service;

@Service
public class FineGreeting implements GreetingService{
   GreetingService greetingService;

   @Autowired
   public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    @Override
    public void greet() {
        System.out.println("From FineGreeting setter Injection");
    }
}
