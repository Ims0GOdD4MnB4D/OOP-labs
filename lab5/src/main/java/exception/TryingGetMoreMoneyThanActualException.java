package exception;

public class TryingGetMoreMoneyThanActualException extends RuntimeException {
    public TryingGetMoreMoneyThanActualException() {
        super("Trying to get more money than there is on a bank account");
    }
}
