package exceptions;

public class NotEnoughProductQuantity extends Exception {
    public NotEnoughProductQuantity() {
        super("There is not enough quantity such product");
    }
}
