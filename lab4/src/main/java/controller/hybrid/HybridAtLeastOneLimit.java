package controller.hybrid;

import controller.cleaning.AbstractCleaningAlgorithm;
import model.Backup.Backup;

import java.util.Arrays;
import java.util.List;

public class HybridAtLeastOneLimit implements AbstractHybridAlgorithm {
    List<AbstractCleaningAlgorithm> cleanerList;

    public HybridAtLeastOneLimit(AbstractCleaningAlgorithm ... cleaners) {
        cleanerList = Arrays.asList(cleaners);
    }

    @Override
    public void hybridCleaningByLimits(Backup backup) {
        for (AbstractCleaningAlgorithm abstractCleaningAlgorithm : cleanerList) {
            abstractCleaningAlgorithm.cleanByLimit(backup);
        }
    }
}
