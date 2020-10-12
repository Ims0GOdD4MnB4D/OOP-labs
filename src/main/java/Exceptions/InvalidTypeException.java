package Exceptions;

public class InvalidTypeException extends Exception{

    public InvalidTypeException() {

        super("Invalid type of property value");
    }

    public InvalidTypeException(String message) {
        super(message);
    }
}
