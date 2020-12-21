package exceptions;

public class NoCommitsFound extends RuntimeException {
    public NoCommitsFound() {
        super("No commits were found. Task logger is clear");
    }
}
