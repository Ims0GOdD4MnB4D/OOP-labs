package controller.cleaning.simpe_cleaning;

import controller.cleaning.AbstractCleaningAlgorithm;
import model.Backup.Backup;

public interface AbstractSimpleCleaningAlgorithm extends AbstractCleaningAlgorithm {
    boolean isCleaningNeeded(Backup backup);
}
