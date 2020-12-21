package exceptions;

public class DeadlineIsExpiredException extends RuntimeException {
    public DeadlineIsExpiredException() {
        super("Deadline is expired");
    }
}
