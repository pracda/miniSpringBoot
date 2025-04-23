package Application;


import framework.Autowired;
import framework.Scheduled;
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
    @Scheduled(5000)
    public void greet() {
      printService.greet();
    }
}
