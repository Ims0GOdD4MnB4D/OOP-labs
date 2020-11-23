package controller.cleaning;

import model.Backup.Backup;

public class CleaningBySize implements AbstractCleaningAlgorithm {
    private final int maxSize;

    public CleaningBySize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void cleanByLimit(Backup backup) {
        for(int i=backup.getRpList().size()-1; i>=0; --i) {
            if (backup.getRestorePointsSize() >= maxSize)
                backup.deleteRestorePoint(backup.getRpList().get(
                        backup.getRpList().size() - i - 1).getRpId());
            else
                break;
        }
//            backup.getRpList().forEach(rp -> {
//            if(backup.getBackupSize() > maxSize)
//                backup.deleteRestorePoint(rp.getRpId());
//        });
    }
}
