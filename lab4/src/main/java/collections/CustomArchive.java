package collections;

import java.io.File;
import java.util.List;

public class CustomArchive {
    private final List<File> archivedFiles;

    public CustomArchive(List<File> filesToArchive) {
        archivedFiles = filesToArchive;
    }

    public List<File> unzip() {
        return archivedFiles;
    }
}
