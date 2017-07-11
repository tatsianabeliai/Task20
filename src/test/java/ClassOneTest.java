import com.sun.org.glassfish.gmbal.Description;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class ClassOneTest extends TxtDataProvider {
    private static final Logger LOG = Logger.getLogger(ClassOneTest.class);
    private final static int NUMBER_ONE = 50;
    private final static int NUMBER_TWO = 5;
    private final static int SUM_RESULT = 55;
    private final static int DEDUCT_RESULT = 45;
    private final static int MULTIPLY_RESULT = 250;
    private final static int DIVIDE_RESULT = 10;
    private Mathematics mathematics;

    @BeforeClass(alwaysRun = true)
    public void onceExecutedBeforeAll() {
        LOG.info("@BeforeClass: executedBeforeAllTests");
        mathematics = new Mathematics();
    }

    @BeforeMethod(alwaysRun = true)
    public void executedBeforeEachTest() {
        LOG.info("@Before: executedBeforeEachTest");
        mathematics.setResult(0);
    }

    @AfterMethod
    public void executedAfterEachTest() {
        LOG.info("@After: executedAfterEachTest");
    }

    @AfterClass
    public void onceExecutedAfterAll() {
        LOG.info("@AfterClass: executedAfterAllTests");
    }

    @Test(priority = 5, groups = {"smoke", "fast"})
    public void addTest() {
        mathematics.add(NUMBER_ONE, NUMBER_TWO);
        int result = mathematics.getResult();
        Assert.assertEquals(SUM_RESULT, result, "Not expected result is displayed");
    }

    @Test(priority = 3, timeOut = 500, groups = {"fast"}, dependsOnMethods = "addTest")
    public void deductTest() throws InterruptedException {
        Thread.sleep(1500);
        mathematics.deduct(NUMBER_ONE, NUMBER_TWO);
        int result = mathematics.getResult();
        Assert.assertEquals(DEDUCT_RESULT, result, "Not expected result is displayed");
    }

    @Test(priority = 4, groups = {"fast"})
    public void multiplyTest() {
        mathematics.multiply(NUMBER_ONE, NUMBER_TWO);
        int result = mathematics.getResult();
        Assert.assertEquals(MULTIPLY_RESULT, result, "Not expected result is displayed");
    }

    @Test(priority = 2, groups = {"fast"})
    public void divideTest() {
        mathematics.divide(NUMBER_ONE, NUMBER_TWO);
        int result = mathematics.getResult();
        Assert.assertEquals(DIVIDE_RESULT, result, "Not expected result is displayed");
    }

    @Test(priority = 1, groups = {"fast"})
    public void divideByZeroTest() {
        mathematics.divide(NUMBER_ONE, 0);
        int result = mathematics.getResult();
        Assert.assertEquals(DIVIDE_RESULT, result);
    }

    @Description("The test gets data from testngDDTTest.xml file. Run testngDDTTest.xml")
    @Test(groups = "fast", dataProvider = "txtDataProvider")
    public void DDTTest(int numberX, int numberY, String operation, int expectedResult) {
        int result = 0;
        switch (operation) {
            case "add":
                mathematics.add(numberX, numberY);
                result = mathematics.getResult();
                break;
            case "deduct":
                mathematics.deduct(numberX, numberY);
                result = mathematics.getResult();
                break;
            case "divide":
                mathematics.divide(numberX, numberY);
                result = mathematics.getResult();
                break;
            case "multiply":
                mathematics.multiply(numberX, numberY);
                result = mathematics.getResult();
                break;
            default:
                Assert.fail("incorrect operation type");
        }
        Assert.assertEquals(result, expectedResult, "The result is not correct");
    }
}
