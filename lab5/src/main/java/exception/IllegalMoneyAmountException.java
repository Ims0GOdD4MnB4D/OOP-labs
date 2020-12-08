package exception;

public class IllegalMoneyAmountException extends RuntimeException {
    public IllegalMoneyAmountException() {
        super("Illegal money value");
    }
}
