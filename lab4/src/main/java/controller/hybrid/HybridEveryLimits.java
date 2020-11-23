package controller.hybrid;

import model.Backup.Backup;

import java.time.LocalDateTime;

public class HybridEveryLimits implements AbstractHybridAlgorithm {
    private LocalDateTime outdatedPoints;
    private final int rpAmount;
    private final int maxSize;

    public HybridEveryLimits(int maxSize, int rpAmount) {
        this.maxSize = maxSize;
        this.rpAmount = rpAmount;
    }

    @Override
    public void hybridCleaningByLimits(Backup backup) {
        for(int i=backup.getRpList().size()-1; i>=0; --i) {
            if (backup.getRestorePointsSize() >= maxSize || i + 1 > rpAmount)
                backup.deleteRestorePoint(backup.getRpList().get(
                        backup.getRpList().size() - i - 1).getRpId());
            else
                break;
        }
//        backup.getRpList().forEach(rp -> {
//            if(rp.getCreationTime().isBefore(outdatedPoints) &&
//                    backup.getRpList().indexOf(rp)
//                            < backup.getRpList().size() - rpAmount + 1)
//                backup.deleteRestorePoint(rp.getRpId());
//        });
    }
}
