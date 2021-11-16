package exceptions;

public class PropertyNotFoundException extends Exception {

    public PropertyNotFoundException() {

        super("Property not found");
    }

    public PropertyNotFoundException(String message) {
        super(message);
    }
}
