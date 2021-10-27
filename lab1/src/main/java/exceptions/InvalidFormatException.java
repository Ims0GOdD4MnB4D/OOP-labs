package exceptions;

public class InvalidFormatException extends Exception {

    public InvalidFormatException() {
        super("Invalid line format");
    }

    public InvalidFormatException(String msg) {
        super(msg);
    }


    public InvalidFormatException(Throwable cause) {
        super("Invalid format", cause);
    }
}
