package controller.cleaning;

import model.Backup.Backup;
import model.RestorePoint.RestorePoint;

import java.time.LocalDateTime;

public class CleaningByDate implements AbstractCleaningAlgorithm {
    private final LocalDateTime outdatedPoints;

    public CleaningByDate(LocalDateTime outdatedPoints) {
        this.outdatedPoints = outdatedPoints;
    }

    @Override
    public void cleanByLimit(Backup backup) {
        for(int i=backup.getRpList().size()-1; i>=0; --i) {
            if (backup.getRpList().get(i).getCreationTime().isBefore(outdatedPoints))
                backup.deleteRestorePoint(backup.getRpList().get(i).getRpId());
        }
//        backup.getRpList().forEach(rp -> {
//            if(rp.getCreationTime().isBefore(outdatedPoints))
//                backup.deleteRestorePoint(rp.getRpId());
//        });
        }
    }
