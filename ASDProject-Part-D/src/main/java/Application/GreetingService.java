package Application;


import framework.Service;

@Service
public class GreetingService {

    public GreetingService() {
    }
    public void print(String message) {
        System.out.println( "Greeting Service "+message);
    }
}

