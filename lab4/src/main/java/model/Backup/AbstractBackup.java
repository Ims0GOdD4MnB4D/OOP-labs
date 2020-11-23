package model.Backup;

import model.RestorePoint.RestorePoint;
import model.RestorePoint.RestorePointDefault;
import model.RestorePoint.RestorePointIncremental;
import model.RestorePoint.RestorePointInfo;

import java.util.UUID;

public interface AbstractBackup {
    void addRestorePoint(RestorePoint restorePoint);
    void deleteRestorePoint(UUID rpId);
}
