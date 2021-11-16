package model.Backup;

import exceptions.DirectoryEmptyException;
import exceptions.DirectoryNotFoundException;
import exceptions.FirstPointIncrementalException;
import model.RestorePoint.RestorePoint;
import model.RestorePoint.RestorePointDefault;
import model.RestorePoint.RestorePointIncremental;
import model.RestorePoint.RestorePointInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Backup implements AbstractBackup {

    private final List<RestorePoint> rpList;
    private final ArrayList backupStorage;
    private final List<RestorePointInfo> curRestorePoints;
    private final UUID backupId;
    private Integer restorePointsSize;

    public Backup(File dir) {
        if (!dir.exists())
            throw new DirectoryNotFoundException(dir.toString());
        if (Objects.requireNonNull(dir.list()).length == 0)
            throw new DirectoryEmptyException(dir);
        rpList = new ArrayList<>();
        curRestorePoints = new ArrayList<>();
        backupStorage = new ArrayList(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
        restorePointsSize = 0;
        backupId = new UUID(Integer.MAX_VALUE, 0);
    }

    @Override
    public void addRestorePoint(RestorePoint restorePoint) {
        if (restorePoint instanceof RestorePointDefault)
            addRestorePointDefault(restorePoint);
        else
            addRestorePointIncremental(restorePoint);
        restorePointsSize += restorePoint.getRestorePointSize();
    }

    public void addFile(File newFile) {
        if (newFile.isDirectory())
            backupStorage.addAll(Arrays.asList(Objects.requireNonNull(newFile.listFiles())));
        else
            backupStorage.add(newFile);
    }

    public void removeFile(File rmFile) {
        backupStorage.forEach(file -> {
            if (file.equals(rmFile)) {
                backupStorage.remove(file);
            }
        });
    }

    private void addRestorePointDefault(RestorePoint restorePoint) {
        rpList.add(restorePoint);
        curRestorePoints.add(new RestorePointInfo(restorePoint));
    }

    private void addRestorePointIncremental(RestorePoint restorePoint) {
        if (rpList.size() < 1)
            throw new FirstPointIncrementalException();
        for (int i = rpList.size() - 1; i >= 0; --i) {
            if (rpList.get(i) instanceof RestorePointDefault) {
                rpList.add(countDelta(restorePoint, rpList.get(i)));
                ((RestorePointIncremental) rpList.get(rpList.size() - 1))
                        .setDepPoint((RestorePointDefault) rpList.get(i));
                ((RestorePointDefault) rpList.get(i)).incrementDependency();
                break;
            }
        }
        curRestorePoints.add(new RestorePointInfo(restorePoint));
    }

    private RestorePoint countDelta(RestorePoint restorePoint, RestorePoint diffPoint) {
        //TODO: count delta as difference in files and save it
        restorePoint.getFileList().removeIf(
                item -> diffPoint.getFileList().contains(item));
        return restorePoint;
    }

    @Override
    public void deleteRestorePoint(UUID rpId) {
        for (RestorePoint item : rpList) {
            if (rpId.equals(item.getRpId())) {
                if (item instanceof RestorePointIncremental) {
                    restorePointsSize -= item.getRestorePointSize();
                    ((RestorePointIncremental) item).getDependent().decrementDependency();
                    rpList.remove(item);
                    return;
                } else if (((RestorePointDefault) item).getDependency() == 0) {
                    restorePointsSize -= item.getRestorePointSize();
                    rpList.remove(item);
                    return;
                }
            }
        }
    }

    public void replacePoints(List<RestorePoint> newRpList) {
        this.rpList.clear();
        for (RestorePoint item : newRpList) {
            addRestorePoint(item);
        }
    }

    public List<RestorePointInfo> getCurRestorePoints() {
        return curRestorePoints;
    }

    public List<File> getBackupStorage() {
        return backupStorage;
    }

    public List<RestorePoint> getRpList() {
        return rpList;
    }

    public UUID getBackupId() {
        return backupId;
    }

    public Integer getRestorePointsSize() {
        return restorePointsSize;
    }
}
