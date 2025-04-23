package org.example.application;


import org.example.framework.Service;

@Service
public class GreetingService {//implements GreetingService{

    public void greet() {
        System.out.println("Greetings, dear user!");
    }
}