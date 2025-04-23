package Application;


import framework.Async;
import framework.Profile;
import framework.Service;

import java.beans.Customizer;

@Service
//@Profile("dev")
public class GreetingService implements IGreetingService {
    @Override
    public Person addCustomer(String name, String surname,String email, String street, String city, String state, String zip) {
        Person person = new Person(name,surname,email);
        Address address = new Address(street, city, state, zip);
        person.setAddress(address);
        return person;

    }

    @Async
    public void addCustomerAsync(String name, String surname, String email, String street, String city, String state, String zip) {
        // method implementation
        System.out.println("Adding customer asynchronously...");
        try {
            Thread.sleep(2000); // simulate a long-running operation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Customer added successfully!");
    }

}

