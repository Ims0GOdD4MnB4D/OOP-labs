package Exceptions;

public class SectionNotFoundException extends Exception{

    public SectionNotFoundException() {

        super("Section not found");
    }

    public SectionNotFoundException(String message) {
        super(message);
    }
}
