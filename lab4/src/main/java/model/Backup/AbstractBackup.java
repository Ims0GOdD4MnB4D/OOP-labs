package model.Backup;

import model.RestorePoint.RestorePoint;

import java.util.UUID;

public interface AbstractBackup {
    void addRestorePoint(RestorePoint restorePoint);

    void deleteRestorePoint(UUID rpId);
}
