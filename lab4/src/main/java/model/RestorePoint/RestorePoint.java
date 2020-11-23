package model.RestorePoint;

import collections.CustomArchive;
import exceptions.DirectoryEmptyException;
import exceptions.DirectoryNotFoundException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public abstract class RestorePoint implements AbstractRestorePoint {
    private  CustomArchive archive;
    private  List<File> fileList;
    private final UUID rpId = new UUID(Integer.MAX_VALUE, 0);
    private final LocalDateTime creationTime = LocalDateTime.now();

    public RestorePoint(CustomArchive archive) {
        this.archive = archive;
    }

    public RestorePoint(List<File> fileList) {
        this.fileList = fileList;
    }

    public RestorePoint(String directoryPath) {
        File dir = new File(directoryPath);
        if(!dir.exists())
            throw new DirectoryNotFoundException(directoryPath);
        if(Objects.requireNonNull(dir.list()).length == 0)
            throw new DirectoryEmptyException(dir);
        fileList = new java.util.ArrayList<>(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
    }

    @Override
    public int getRestorePointSize() {
        int size = 0;
        if(fileList != null)
            for(File item : fileList) {
            size += item.length();
            }
        else
            for(File item : archive.unzip()) {
                size += item.length();
            }
        return size;
    }

    public UUID getRpId() {
        return rpId;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
