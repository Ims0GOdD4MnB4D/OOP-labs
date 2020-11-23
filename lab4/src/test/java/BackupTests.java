import controller.BackupManager.BackupManager;
import controller.cleaning.simpe_cleaning.CleaningByPointAmount;
import controller.cleaning.simpe_cleaning.CleaningBySize;
import controller.creating.CreatingFolderStorage;
import controller.cleaning.hybrid.HybridAtLeastOneLimit;
import controller.cleaning.hybrid.HybridEveryLimits;
import exceptions.RemovingDependent;
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
        BackupManager backupManager = 
                new BackupManager(new Backup(new File("src/main/resources/case 1")));
        backupManager.addCreatingAlgorithm(new CreatingFolderStorage());
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.addCleaningAlgorithm(new CleaningBySize(14));
        backupManager.activateCleaningAlgorithm();
        List<RestorePoint> expected = new ArrayList<>();
        Assert.assertEquals(expected, backupManager.getBackup().getRpList());
    }

    @Test
    public void test2() {
        BackupManager backupManager =
                new BackupManager(new Backup(new File("src/main/resources/case 1")));
        backupManager.addCreatingAlgorithm(new CreatingFolderStorage());
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.activateCreatingDefaultRPAlgorithm();
        int expected = 1;
        backupManager.addCleaningAlgorithm(new CleaningByPointAmount(1));
        backupManager.activateCleaningAlgorithm();
        Assert.assertEquals(expected, backupManager.getBackup().getRpList().size());
    }

    @Test
    public void test3() {
        BackupManager backupManager =
                new BackupManager(new Backup(new File("src/main/resources/case 2")));
        backupManager.addCreatingAlgorithm(new CreatingFolderStorage());
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.addCleaningAlgorithm(new CleaningBySize(250));
        backupManager.activateCleaningAlgorithm();
        int expected = 1;
        Assert.assertEquals(expected, backupManager.getBackup().getRpList().size());
    }

    @Test
    public void test4() {
        BackupManager backupManager =
                new BackupManager(new Backup(new File("src/main/resources/case 1")));
        backupManager.addCreatingAlgorithm(new CreatingFolderStorage());
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.activateCreatingIncrementalRPAlgorithm();
        int expected = 2;
        Assert.assertEquals(expected, backupManager.getBackup().getRpList().size());
    }

    @Test
    public void test5() {
        BackupManager backupManager =
                new BackupManager(new Backup(new File("src/main/resources/case 2")));
        backupManager.addCreatingAlgorithm(new CreatingFolderStorage());
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.addCleaningAlgorithm(new HybridEveryLimits(
                new CleaningBySize(250),
                new CleaningByPointAmount(1)
        ));
        backupManager.activateCleaningAlgorithm();
        int expected = 1;
        Assert.assertEquals(expected, backupManager.getBackup().getRpList().size());
    }

    @Test
    public void test6() {
        BackupManager backupManager =
                new BackupManager(new Backup(new File("src/main/resources/case 2")));
        backupManager.addCreatingAlgorithm(new CreatingFolderStorage());
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.addCleaningAlgorithm(
                new HybridAtLeastOneLimit(
                        new CleaningByPointAmount(1),
                        new CleaningBySize(150)));
        backupManager.activateCleaningAlgorithm();
        int expected = 0;
        Assert.assertEquals(expected, backupManager.getBackup().getRpList().size());
    }

    @Test
    public void test7() {
        BackupManager backupManager =
                new BackupManager(new Backup(new File("src/main/resources/case 1")));
        backupManager.addCreatingAlgorithm(new CreatingFolderStorage());
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.activateCreatingIncrementalRPAlgorithm();
        backupManager.activateCreatingIncrementalRPAlgorithm();
        backupManager.activateCreatingDefaultRPAlgorithm();
        backupManager.activateCreatingIncrementalRPAlgorithm();
        backupManager.activateCreatingIncrementalRPAlgorithm();
        backupManager.activateCreatingIncrementalRPAlgorithm();
        backupManager.addCleaningAlgorithm(new CleaningByPointAmount(3));
        backupManager.activateCleaningAlgorithm();
        int expected = 3;
        Assert.assertEquals(expected, backupManager.getBackup().getRpList().size());
    }
}
