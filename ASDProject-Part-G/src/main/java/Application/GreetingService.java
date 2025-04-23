package Application;


import framework.Profile;
import framework.Service;

@Service
@Profile("dev")
public class GreetingService implements IGreetingService {

    public GreetingService() {
    }
    public void greet() {
        System.out.println( "Greeting Service ");
    }
}

