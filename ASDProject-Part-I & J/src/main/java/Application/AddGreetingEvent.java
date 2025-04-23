package Application;

public class AddGreetingEvent {
    private String message;
    public AddGreetingEvent(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
