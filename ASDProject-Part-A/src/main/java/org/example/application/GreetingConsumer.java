package org.example.application;


import org.example.framework.Autowired;
import org.example.framework.Service;

// Service consumer
@Service
public class GreetingConsumer {
    @Autowired
    private GreetingService greetingService;

    public void displayGreeting() {
        greetingService.greet();
    }
}