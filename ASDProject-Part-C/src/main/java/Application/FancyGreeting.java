package Application;


import framework.Autowired;
import framework.Service;
import framework.Value;

@Service
public class FancyGreeting implements IGreetingService {
    @Autowired
    IGreetingService printService;

    @Value("color")
    private String color;
    public void greet() {
       System.out.println("From Fancy Greeting Field Injection. And the and today's color is "+ color);
    }
}
