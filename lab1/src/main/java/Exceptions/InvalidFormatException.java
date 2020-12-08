package Exceptions;

public class InvalidFormatException extends RuntimeException{
    public InvalidFormatException() {
        super("Invalid line format");
    }
}
