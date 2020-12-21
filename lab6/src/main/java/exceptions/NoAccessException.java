package exceptions;

public class NoAccessException extends RuntimeException {
    public NoAccessException() {
        super("You have no rights for this action");
    }
}
