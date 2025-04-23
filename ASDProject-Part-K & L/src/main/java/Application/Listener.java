package Application;

import framework.Async;
import framework.EventListener;
import framework.Service;

@Service
public class Listener {
    @Async
    @EventListener
    public void onEvent(NewGreetingEvent event) {
        System.out.println("received event :" + event.getPerson());;
    }
}
