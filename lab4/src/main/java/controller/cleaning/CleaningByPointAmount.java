package controller.cleaning;

import model.Backup.Backup;

import java.util.stream.Collectors;

public class CleaningByPointAmount implements AbstractCleaningAlgorithm {
    private final int rpAmount;

    public CleaningByPointAmount(int rpAmount) {
        this.rpAmount = rpAmount;
    }

    @Override
    public void cleanByLimit(Backup backup) {
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

