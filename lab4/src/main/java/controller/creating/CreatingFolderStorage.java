package controller.creating;

import model.Backup.Backup;
import model.RestorePoint.RestorePointDefault;
import model.RestorePoint.RestorePointIncremental;

public class CreatingFolderStorage implements AbstractCreatingAlgorithm {

    @Override
    public void createRestorePointDefault(Backup backup) {
        RestorePointDefault newRp = new RestorePointDefault(
                backup.getBackupStorage());
        backup.addRestorePoint(newRp);
    }

    @Override
    public void createRestorePointIncremental(Backup backup) {
        RestorePointIncremental newRp = new RestorePointIncremental(
                backup.getBackupStorage());
        backup.addRestorePoint(newRp);
    }
}
