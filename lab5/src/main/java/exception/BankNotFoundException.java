package exception;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException() {
        super("There is no bank with account with such ID in current bank list");
    }
}
