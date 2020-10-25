package exceptions;

public class NoSuchShopFound extends Exception{
    public NoSuchShopFound() {
        super("There is no shop with such product package list");
    }
}
