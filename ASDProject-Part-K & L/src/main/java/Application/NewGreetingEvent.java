package Application;

public class NewGreetingEvent {
    private Person person;

    public NewGreetingEvent(Person person) {
        super();
        this.person = person;
    }
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
