package framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


public interface ApplicationEventPublisher {
    void publishEvent(Object event);
}
