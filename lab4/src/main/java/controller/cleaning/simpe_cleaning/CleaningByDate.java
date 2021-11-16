package controller.cleaning.simpe_cleaning;

import model.Backup.Backup;

import java.time.LocalDateTime;

public class CleaningByDate implements AbstractSimpleCleaningAlgorithm {
    private final LocalDateTime outdatedPoints;

    public CleaningByDate(LocalDateTime outdatedPoints) {
        this.outdatedPoints = outdatedPoints;
    }

    @Override
    public void clean(Backup backup) {
        while (isCleaningNeeded(backup)) {
            backup.deleteRestorePoint(backup.getRpList().get(0).getRpId());
        }
    }

    @Override
    public boolean isCleaningNeeded(Backup backup) {
        return backup.getRpList()
                .get(0)
                .getCreationTime()
                .isBefore(outdatedPoints);
    }
}
