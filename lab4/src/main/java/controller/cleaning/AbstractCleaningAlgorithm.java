package controller.cleaning;

import model.Backup.Backup;

public interface AbstractCleaningAlgorithm {
    void clean(Backup backup);
}
