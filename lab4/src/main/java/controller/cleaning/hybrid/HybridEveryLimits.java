package controller.cleaning.hybrid;

import controller.cleaning.simpe_cleaning.AbstractSimpleCleaningAlgorithm;
import model.Backup.Backup;

import java.util.Arrays;
import java.util.List;

public class HybridEveryLimits implements AbstractHybridAlgorithm {
    List<AbstractSimpleCleaningAlgorithm> cleanerList;

    public HybridEveryLimits(AbstractSimpleCleaningAlgorithm... cleaners) {
        cleanerList = Arrays.asList(cleaners);
    }

    @Override
    public void clean(Backup backup) {
        for(AbstractSimpleCleaningAlgorithm item : cleanerList) {
            if(!item.isCleaningNeeded(backup))
                return;
        }
        for (AbstractSimpleCleaningAlgorithm abstractCleaningAlgorithm : cleanerList) {
            abstractCleaningAlgorithm.clean(backup);
        }
    }
}
