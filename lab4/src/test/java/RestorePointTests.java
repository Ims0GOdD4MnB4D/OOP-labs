import exceptions.DirectoryEmptyException;
import exceptions.DirectoryNotFoundException;
import model.RestorePoint.RestorePoint;
import model.RestorePoint.RestorePointDefault;
import model.RestorePoint.RestorePointIncremental;
import org.junit.Assert;
import org.junit.Test;

public class RestorePointTests {
    @Test
    public void test1() {
        RestorePointDefault rp = new RestorePointDefault("src/main/resources/case 1");
        int expected = 15;
        Assert.assertEquals(rp.getRestorePointSize(), expected);
    }

    @Test
    public void test2() {
        RestorePointIncremental rp = new RestorePointIncremental("src/main/resources/case 1");
        int expected = 15;
        Assert.assertEquals(rp.getRestorePointSize(), expected);
    }

    @Test(expected = DirectoryNotFoundException.class)
    public void DirectoryNotFoundExceptionTest() {
        RestorePoint rp = new RestorePointDefault("src/main/resources/non-existent folder");
    }

    @Test(expected = DirectoryEmptyException.class)
    public void DirectoryEmptyExceptionTest() {
        RestorePoint rp = new RestorePointDefault("src/main/resources/empty folder");
    }

}
