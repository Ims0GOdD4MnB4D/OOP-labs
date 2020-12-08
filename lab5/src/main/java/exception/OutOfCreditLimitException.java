package exception;

public class OutOfCreditLimitException extends RuntimeException {
    public OutOfCreditLimitException() {
        super("Balance withdrawing would be out of credit limit");
    }
}
