package controller.cleaning.simpe_cleaning;

import model.Backup.Backup;

public class CleaningBySize implements AbstractSimpleCleaningAlgorithm {
    private final int maxSize;

    public CleaningBySize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void clean(Backup backup) {
        while(isCleaningNeeded(backup)) {
                backup.deleteRestorePoint(backup
                        .getRpList().get(0).getRpId());
        }
    }

    @Override
    public boolean isCleaningNeeded(Backup backup) {
        return backup.getRestorePointsSize() >= maxSize;
    }
}
