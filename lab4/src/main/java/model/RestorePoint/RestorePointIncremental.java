package model.RestorePoint;

import collections.CustomArchive;

import java.io.File;
import java.util.List;

public class RestorePointIncremental extends RestorePoint {
    private RestorePointDefault depPoint;

    public RestorePointIncremental(String directoryPath) {
        super(directoryPath);
    }

    public RestorePointIncremental(CustomArchive archive) {
        super(archive);
    }

    public RestorePointIncremental(List<File> fileList) {
        super(fileList);
    }

    public void setDepPoint(RestorePointDefault depPoint) {
        this.depPoint = depPoint;
    }

    public RestorePointDefault getDependent() {
        return depPoint;
    }
}
