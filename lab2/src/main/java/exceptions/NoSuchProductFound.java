package exceptions;

public class NoSuchProductFound extends Exception {
    public NoSuchProductFound() {
        super("There is no such product in current shop");
    }
}
