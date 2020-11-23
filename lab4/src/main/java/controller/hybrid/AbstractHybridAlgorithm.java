package controller.hybrid;

import model.Backup.Backup;

public interface AbstractHybridAlgorithm {
    void hybridCleaningByLimits(Backup backup);
}
