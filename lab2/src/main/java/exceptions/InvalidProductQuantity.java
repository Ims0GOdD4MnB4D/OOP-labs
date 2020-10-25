package exceptions;

public class InvalidProductQuantity extends Exception{
    public InvalidProductQuantity() {
        super("Invalid value of product quantity");
    }
}
