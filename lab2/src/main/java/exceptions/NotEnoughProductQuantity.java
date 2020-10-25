package exceptions;

public class NotEnoughProductQuantity extends Exception{
    public NotEnoughProductQuantity() {
        super("There is no enough quantity such product");
    }
}
