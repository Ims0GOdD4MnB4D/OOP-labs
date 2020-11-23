package controller.cleaning.simpe_cleaning;

import model.Backup.Backup;

public class CleaningByPointAmount implements AbstractSimpleCleaningAlgorithm {
    private final int rpAmount;

    public CleaningByPointAmount(int rpAmount) {
        this.rpAmount = rpAmount;
    }

    @Override
    public void clean(Backup backup) {
        while(isCleaningNeeded(backup)) {
                backup.deleteRestorePoint
                        (backup.getRpList().get(0).getRpId());
        }
    }

    @Override
    public boolean isCleaningNeeded(Backup backup) {
        if(backup.getRpList().isEmpty())
            return false;
        return backup.getRpList().size() > rpAmount;
    }
}

