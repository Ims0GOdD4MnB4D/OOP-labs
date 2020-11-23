package exceptions;

import java.io.File;

public class DirectoryEmptyException extends RuntimeException {
    public DirectoryEmptyException(File dir) {
        super("Directory " + dir + " is empty");
    }
}
