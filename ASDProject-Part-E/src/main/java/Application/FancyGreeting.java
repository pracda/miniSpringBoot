package Application;


import framework.Autowired;
import framework.Service;
import framework.Value;

@Service
public class FancyGreeting{
    @Autowired
    IGreetingService printService;
    public FancyGreeting() {
    }
    @Value("color")
    private String color;
    public void greet() {
      printService.greet();
    }
}
