package Application;

import framework.EventListener;
import framework.Service;

@Service
public class Listener2 {
    @EventListener
    public void onEvent(AddGreetingEvent event) {
        System.out.println("received event :" + event.getMessage());;
    }
}
