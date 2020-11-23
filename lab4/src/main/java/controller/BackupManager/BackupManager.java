package controller.BackupManager;

import controller.cleaning.AbstractCleaningAlgorithm;
import controller.creating.AbstractCreatingAlgorithm;
import controller.hybrid.AbstractHybridAlgorithm;
import model.Backup.Backup;

public class BackupManager implements AbstractBackupManager{
    Backup backup;
    private AbstractCleaningAlgorithm cleaningAlgorithm;
    private AbstractCreatingAlgorithm creatingAlgorithm;
    private AbstractHybridAlgorithm hybridAlgorithm;

    public BackupManager(Backup backup,
                         AbstractCleaningAlgorithm cleaningAlgorithm,
                         AbstractCreatingAlgorithm creatingAlgorithm,
                         AbstractHybridAlgorithm hybridAlgorithm) {
        this.backup = backup;
        this.cleaningAlgorithm = cleaningAlgorithm;
        this.creatingAlgorithm = creatingAlgorithm;
        this.hybridAlgorithm = hybridAlgorithm;
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

    public BackupManager(Backup backup,
                         AbstractHybridAlgorithm hybridAlgorithm) {
        this.backup = backup;
        this.hybridAlgorithm = hybridAlgorithm;
    }

    public void addCreatingAlgorithm(AbstractCreatingAlgorithm alg) {
        creatingAlgorithm = alg;
    }

    public void addCleaningAlgorithm(AbstractCleaningAlgorithm alg) {
        cleaningAlgorithm = alg;
        activateCleaningAlgorithm();
    }

    public void addHybridCleaningAlgorithm(AbstractHybridAlgorithm alg) {
        hybridAlgorithm = alg;
        activateHybridAlgorithm();
    }

    public void activateCleaningAlgorithm() {
        if(cleaningAlgorithm != null)
            cleaningAlgorithm.cleanByLimit(backup);
    }

    public void activateCreatingDefaultRPAlgorithm() {
        if(creatingAlgorithm != null)
            creatingAlgorithm.createRestorePointDefault(backup);
    }

    public void activateCreatingIncrementalRPAlgorithm() {
        if(creatingAlgorithm != null)
            creatingAlgorithm.createRestorePointIncremental(backup);
    }

    public void activateHybridAlgorithm() {
        if(hybridAlgorithm != null)
            hybridAlgorithm.hybridCleaningByLimits(backup);
    }

    public Backup getBackup() {
        return backup;
    }
}
