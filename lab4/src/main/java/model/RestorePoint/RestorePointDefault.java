package model.RestorePoint;

import collections.CustomArchive;

import java.io.File;
import java.util.List;

public class RestorePointDefault extends RestorePoint {
    private int dependencies = 0;

    public RestorePointDefault(String directoryPath) {
        super(directoryPath);
    }

    public RestorePointDefault(List<File> fileList) {
        super(fileList);
    }

    public RestorePointDefault(CustomArchive archive) {
        super(archive);
    }

    public void incrementDependency() {
        ++dependencies;
    }

    public void decrementDependency() {
        if (dependencies > 0)
            --dependencies;
    }

    public int getDependency() {
        return dependencies;
    }
}
