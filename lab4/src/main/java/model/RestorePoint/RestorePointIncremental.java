package model.RestorePoint;

import collections.CustomArchive;

import java.io.File;
import java.util.List;

public class RestorePointIncremental extends RestorePoint {
    private RestorePoint prevPoint;
    public RestorePointIncremental(String directoryPath) {
        super(directoryPath);
    }
//    public RestorePointIncremental(RestorePoint restorePoint) {
//        super();
//    }

    public RestorePointIncremental(CustomArchive archive) {
        super(archive);
    }

    public RestorePointIncremental(List<File> fileList) {
        super(fileList);
    }

    public void setPrevPoint(RestorePoint prevPoint) {
        this.prevPoint = prevPoint;
    }

    public RestorePoint getPrevPoint() {
        return prevPoint;
    }
}
