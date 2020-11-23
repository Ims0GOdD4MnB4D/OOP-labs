import controller.cleaning.CleaningByPointAmount;
import controller.cleaning.CleaningBySize;
import controller.creating.CreatingArchiveStorage;
import controller.creating.CreatingFolderStorage;
import controller.hybrid.HybridAtLeastOneLimit;
import controller.hybrid.HybridEveryLimits;
import exceptions.DirectoryNotFoundException;
import model.Backup.Backup;
import model.RestorePoint.RestorePoint;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BackupTests {
    @Test
    public void test1() {
        Backup backup = new Backup(new File("src/main/resources/case 1"));
        backup.addCreatingAlgorithm(new CreatingFolderStorage());
        backup.activateCreatingDefaultRPAlgorithm();
        backup.addCleaningAlgorithm(new CleaningBySize(14));
        backup.activateCleaningAlgorithm();
        List<RestorePoint> expected = new ArrayList<>();
        Assert.assertEquals(expected, backup.getRpList());
    }

    @Test
    public void test2() {
        Backup backup = new Backup(new File("src/main/resources/case 1"));
        backup.addCreatingAlgorithm(new CreatingFolderStorage());
        backup.activateCreatingDefaultRPAlgorithm();
        backup.activateCreatingDefaultRPAlgorithm();
        int expected = 1;
        backup.addCleaningAlgorithm(new CleaningByPointAmount(1));
        backup.activateCleaningAlgorithm();
        Assert.assertEquals(expected, backup.getRpList().size());
    }

    @Test
    public void test3() {
        Backup backup = new Backup(new File("src/main/resources/case 2"));
        backup.addCreatingAlgorithm(new CreatingFolderStorage());
        backup.activateCreatingDefaultRPAlgorithm();
        backup.activateCreatingDefaultRPAlgorithm();
        backup.addCleaningAlgorithm(new CleaningBySize(250));
        backup.activateCleaningAlgorithm();
        int expected = 1;
        Assert.assertEquals(expected, backup.getRpList().size());
    }

    @Test
    public void test4() {
        Backup backup = new Backup(new File("src/main/resources/case 1"));
        backup.addCreatingAlgorithm(new CreatingFolderStorage());
        backup.activateCreatingDefaultRPAlgorithm();
        backup.activateCreatingIncrementalRPAlgorithm();
        int expected = 2;
        Assert.assertEquals(expected, backup.getRpList().size());
    }

    @Test
    public void test5() {
        Backup backup = new Backup(new File("src/main/resources/case 2"));
        backup.addCreatingAlgorithm(new CreatingFolderStorage());
        backup.activateCreatingDefaultRPAlgorithm();
        backup.activateCreatingDefaultRPAlgorithm();
        backup.addHybridCleaningAlgorithm(new HybridEveryLimits(150, 1));
        backup.activateHybridAlgorithm();
        int expected = 0;
        Assert.assertEquals(expected, backup.getRpList().size());
    }

    @Test
    public void test6() {
        Backup backup = new Backup(new File("src/main/resources/case 2"));
        backup.addCreatingAlgorithm(new CreatingFolderStorage());
        backup.activateCreatingDefaultRPAlgorithm();
        backup.activateCreatingDefaultRPAlgorithm();
        backup.addHybridCleaningAlgorithm(new HybridAtLeastOneLimit(150, 1));
        backup.activateHybridAlgorithm();
        int expected = 1;
        Assert.assertEquals(expected, backup.getRpList().size());
    }
}
