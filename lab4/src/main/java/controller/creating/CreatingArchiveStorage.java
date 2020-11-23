package controller.creating;

import collections.CustomArchive;
import model.Backup.Backup;
import model.RestorePoint.RestorePointDefault;
import model.RestorePoint.RestorePointIncremental;

public class
CreatingArchiveStorage implements AbstractCreatingAlgorithm {

    @Override
    public void createRestorePointDefault(Backup backup) {
        RestorePointDefault newRp = new RestorePointDefault(
                                    new CustomArchive(backup.getBackupStorage()));
        backup.addRestorePoint(newRp);
    }

    @Override
    public void createRestorePointIncremental(Backup backup) {
        RestorePointIncremental newRp = new RestorePointIncremental(
                                        new CustomArchive(backup.getBackupStorage()));
        backup.addRestorePoint(newRp);
    }
}
