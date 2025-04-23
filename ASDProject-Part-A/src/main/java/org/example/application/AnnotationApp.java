package org.example.application;


import org.example.framework.FWContext;

// Application
public class AnnotationApp {
    public static void main(String[] args) {
        FWContext fwContext=new FWContext();
        GreetingConsumer greetingConsumer=(GreetingConsumer) fwContext.getServiceBeanOfType(GreetingConsumer.class);
        greetingConsumer.displayGreeting();

    }
}