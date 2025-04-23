package Application;

import framework.Autowired;
import framework.Service;

@Service
public class BetterGreeting implements GreetingService{
    GreetingService greetingService;
    public BetterGreeting() {
    }
    @Autowired
    public BetterGreeting(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    public void greet() {
        System.out.println("From BetterGreeting Constructor Injection");
    }
}
