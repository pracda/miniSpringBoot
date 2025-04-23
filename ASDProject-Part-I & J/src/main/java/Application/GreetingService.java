package Application;


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


}

