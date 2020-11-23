package exceptions;

public class RemovingDependent extends RuntimeException {
    public RemovingDependent() {
        super("Trying to remove dependent point");
    }
}
