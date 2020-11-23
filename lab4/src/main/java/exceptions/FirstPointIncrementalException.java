package exceptions;

public class FirstPointIncrementalException extends RuntimeException {
    public FirstPointIncrementalException() {
        super("First Restore Point cannot be incremental. Try create default restore point.");
    }
}
