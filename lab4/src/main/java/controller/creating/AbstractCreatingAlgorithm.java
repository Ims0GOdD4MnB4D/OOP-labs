package controller.creating;

import model.Backup.Backup;

public interface AbstractCreatingAlgorithm {
    void createRestorePointIncremental(Backup backup);
    void createRestorePointDefault(Backup backup);
}
