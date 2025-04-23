package Application;

import framework.Autowired;
import framework.Service;

@Service
public class FancyGreetingService implements GreetingService {
    @Autowired
    GreetingService greetingService;

    @Override
    public void greet() {
        System.out.println(" Fancy Greetings, dear user! from Type Injection");
    }
}