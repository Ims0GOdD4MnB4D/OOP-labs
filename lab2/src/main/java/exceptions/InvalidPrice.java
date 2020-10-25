package exceptions;

public class InvalidPrice extends Exception{
    public InvalidPrice() {
        super("Invalid value of price");
    }
}
