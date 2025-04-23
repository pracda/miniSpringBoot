package Application;

import framework.EventListener;
import framework.Service;

@Service
public class Listener {
    @EventListener
    public void onEvent(NewGreetingEvent event) {
        System.out.println("received event :" + event.getPerson());;
    }
}
