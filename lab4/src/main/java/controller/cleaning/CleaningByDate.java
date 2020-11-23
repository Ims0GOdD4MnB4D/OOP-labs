package controller.cleaning;

import model.Backup.Backup;
import model.RestorePoint.RestorePoint;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class CleaningByDate implements AbstractCleaningAlgorithm {
    private final LocalDateTime outdatedPoints;

    public CleaningByDate(LocalDateTime outdatedPoints) {
        this.outdatedPoints = outdatedPoints;
    }

    @Override
    public void cleanByLimit(Backup backup) {
        while(isCleaningNeeded(backup)) {
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
