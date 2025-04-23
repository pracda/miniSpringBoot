package Application;


import framework.Autowired;
import framework.Service;

@Service

public class GoodGreeting implements IGreetingService {


    IGreetingService printService;


    @Autowired
    public void setPrintService(IGreetingService printService) {
      this.printService = printService;
    }

public void greet() {
        System.out.println("From Good Greeting setter Injection");
    }
}
