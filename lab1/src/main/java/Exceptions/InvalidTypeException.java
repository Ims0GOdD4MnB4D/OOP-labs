package Exceptions;


public class InvalidTypeException extends RuntimeException{

    public InvalidTypeException() {
        super("Invalid value type");
    }
}