package Application;

import framework.Profile;
import framework.Service;

@Service
@Profile("test")
public class MockGreetingService implements IGreetingService{
    public void greet() {
        System.out.println("From Mock Greetings");
    }
}
