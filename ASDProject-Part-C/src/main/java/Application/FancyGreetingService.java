package Application;

import framework.Autowired;
import framework.Service;
import framework.Value;

@Service
public class FancyGreetingService implements IGreetingService {
    @Autowired
    GreetingService greetingService;

    @Value("FancyGreetingService")
    private String name;

    @Override
    public void greet() {
        System.out.println(" Fancy Greetings, dear user! from Type Injection with value" + name);
    }
}