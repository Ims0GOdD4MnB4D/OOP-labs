package controller.BackupManager;

import controller.cleaning.AbstractCleaningAlgorithm;
import controller.creating.AbstractCreatingAlgorithm;
import model.Backup.Backup;

public class BackupManager implements AbstractBackupManager {
    private final Backup backup;
    private AbstractCreatingAlgorithm creatingAlgorithm;
    private AbstractCleaningAlgorithm cleaningAlgorithm;

    public BackupManager(Backup backup,
                         AbstractCreatingAlgorithm creatingAlgorithm,
                         AbstractCleaningAlgorithm cleaningAlgorithm) {
        this.backup = backup;
        this.creatingAlgorithm = creatingAlgorithm;
        this.cleaningAlgorithm = cleaningAlgorithm;
    }


    public BackupManager(Backup backup) {
        this.backup = backup;
    }


    public BackupManager(Backup backup,
                         AbstractCleaningAlgorithm cleaningAlgorithm) {
        this.backup = backup;
        this.cleaningAlgorithm = cleaningAlgorithm;
    }

    public BackupManager(Backup backup,
                         AbstractCreatingAlgorithm creatingAlgorithm) {
        this.backup = backup;
        this.creatingAlgorithm = creatingAlgorithm;
    }


    public void addCreatingAlgorithm(AbstractCreatingAlgorithm alg) {
        creatingAlgorithm = alg;
    }

    public void addCleaningAlgorithm(AbstractCleaningAlgorithm alg) {
        cleaningAlgorithm = alg;
        activateCleaningAlgorithm();
    }

    public void activateCleaningAlgorithm() {
        if (cleaningAlgorithm != null)
            cleaningAlgorithm.clean(backup);
    }

    public void activateCreatingDefaultRPAlgorithm() {
        if (creatingAlgorithm != null)
            creatingAlgorithm.createRestorePointDefault(backup);
    }

    public void activateCreatingIncrementalRPAlgorithm() {
        if (creatingAlgorithm != null)
            creatingAlgorithm.createRestorePointIncremental(backup);
    }


    public Backup getBackup() {
        return backup;
    }
}
