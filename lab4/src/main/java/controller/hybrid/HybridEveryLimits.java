package controller.hybrid;

import controller.cleaning.AbstractCleaningAlgorithm;
import model.Backup.Backup;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class HybridEveryLimits implements AbstractHybridAlgorithm {
    List<AbstractCleaningAlgorithm> cleanerList;

    public HybridEveryLimits(AbstractCleaningAlgorithm ... cleaners) {
        cleanerList = Arrays.asList(cleaners);
    }

    @Override
    public void hybridCleaningByLimits(Backup backup) {
        for(AbstractCleaningAlgorithm item : cleanerList) {
            if(!item.isCleaningNeeded(backup))
                return;
        }
        for (AbstractCleaningAlgorithm abstractCleaningAlgorithm : cleanerList) {
            abstractCleaningAlgorithm.cleanByLimit(backup);
        }
    }
}
