package exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("There is no account with such ID in current bank");
    }
}
