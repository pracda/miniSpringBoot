package Application;

import framework.Async;
import framework.EventListener;
import framework.Service;

@Service
public class Listener2 {
    @Async
    @EventListener
    public void onEvent(AddGreetingEvent event) {
        System.out.println("received event :" + event.getMessage());;
    }
}
