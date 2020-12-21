package exceptions;

public class NoEventsWasFoundInLoggerException extends RuntimeException {
    public NoEventsWasFoundInLoggerException(String event) {
        super("There is " + event + " event in logger");
    }
}
