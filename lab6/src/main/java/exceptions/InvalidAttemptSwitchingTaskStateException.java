package exceptions;

public class InvalidAttemptSwitchingTaskStateException extends RuntimeException {
    public InvalidAttemptSwitchingTaskStateException() {
        super("State already have been resolved.");
    }
}
