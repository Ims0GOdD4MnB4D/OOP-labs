package controller.cleaning.hybrid;

import controller.cleaning.simpe_cleaning.AbstractSimpleCleaningAlgorithm;
import model.Backup.Backup;

import java.util.Arrays;
import java.util.List;

public class HybridAtLeastOneLimit implements AbstractHybridAlgorithm {
    List<AbstractSimpleCleaningAlgorithm> cleanerList;

    public HybridAtLeastOneLimit(AbstractSimpleCleaningAlgorithm... cleaners) {
        cleanerList = Arrays.asList(cleaners);
    }

    @Override
    public void clean(Backup backup) {
        for (AbstractSimpleCleaningAlgorithm abstractCleaningAlgorithm : cleanerList) {
            abstractCleaningAlgorithm.clean(backup);
        }
    }
}
