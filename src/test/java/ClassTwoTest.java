import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class ClassTwoTest {
    private static final Logger LOG = Logger.getLogger(ClassTwoTest.class);

    @Test(enabled = false)
    public void emptyMethodTest() {
        LOG.info("This is the empty method");
    }
}
