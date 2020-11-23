package exceptions;

public class DirectoryNotFoundException extends RuntimeException {
    public DirectoryNotFoundException(String dir) {
        super("Could not find directory with " + dir + " path");
    }
}
