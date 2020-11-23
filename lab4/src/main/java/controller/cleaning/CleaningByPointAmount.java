package controller.cleaning;

import model.Backup.Backup;

public class CleaningByPointAmount implements AbstractCleaningAlgorithm {
    private final int rpAmount;

    public CleaningByPointAmount(int rpAmount) {
        this.rpAmount = rpAmount;
    }

    @Override
    public void cleanByLimit(Backup backup) {
        for(int i=backup.getRpList().size()-1; i>=0; --i) {
            if (i + 1 > rpAmount)
                backup.deleteRestorePoint(backup.getRpList().get(
                        backup.getRpList().size() - i - 1).getRpId());
            else
                break;
        }
//        backup.getRpList().forEach(rp -> {
//                if(backup.getRpList().indexOf(rp)
//                        < backup.getRpList().size() - rpAmount + 1)
//                    backup.deleteRestorePoint(rp.getRpId());
//        });
    }
}

