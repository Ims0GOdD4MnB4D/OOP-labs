package exceptions;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException() {
        super("Entity not found");
    }
}
