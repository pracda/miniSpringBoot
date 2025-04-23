package Application;


import framework.Autowired;
import framework.Service;
import framework.Value;

@Service
public class BetterGreeting implements IGreetingService {

    @Value("black")
    private double number;
    public BetterGreeting() {
    }
    IGreetingService printService;
    @Autowired
    public BetterGreeting(IGreetingService printService) {
        this.printService = printService;

    }
    public void greet() {
        System.out.println("From Better Greeting constructor Injection. This value of the number is "+ number);
    }


}




