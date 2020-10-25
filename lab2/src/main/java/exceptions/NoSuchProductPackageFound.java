package exceptions;

public class NoSuchProductPackageFound extends Exception{
    public NoSuchProductPackageFound() {
        super("There is no such product pacakge is current shop");
    }
}
