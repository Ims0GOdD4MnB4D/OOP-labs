package exceptions;

public class EntityExistsInDBException extends RuntimeException {
    public EntityExistsInDBException() {
        super("Entity already exists in database");
    }
}
