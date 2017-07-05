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
    public void DDTTest(int numberX, int numberY) {
        int expectedSum = numberX + numberY;
        int expectedDeduct = numberX - numberY;
        int expectedMultiply = numberX * numberY;
        int expectedDivide = numberX / numberY;

        mathematics.add(numberX, numberY);
        int sumResult = mathematics.getResult();
        Assert.assertEquals(sumResult, expectedSum, "Not expected result: ");

        mathematics.deduct(numberX, numberY);
        int deductResult = mathematics.getResult();
        Assert.assertEquals(deductResult, expectedDeduct, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.multiply(numberX, numberY);
        int multiplyResult = mathematics.getResult();
        Assert.assertEquals(multiplyResult, expectedMultiply, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.divide(numberX, numberY);
        int divideResult = mathematics.getResult();
        Assert.assertEquals(divideResult, expectedDivide, "Not expected result: ");
    }

    @Description("The test gets data from testData.txt file.")
    @Test(groups = "fast", dataProvider = "txtDataProvider")
    public void DDTWithTxtDataProviderTest(int numberOne, int numberTwo) {
        int expectedSum = numberOne + numberTwo;
        int expectedDeduct = numberOne - numberTwo;
        int expectedMultiply = numberOne * numberTwo;
        int expectedDivide = numberOne / numberTwo;

        mathematics.add(numberOne, numberTwo);
        int sumResult = mathematics.getResult();
        Assert.assertEquals(sumResult, expectedSum, "Not expected result: ");

        mathematics.deduct(numberOne, numberTwo);
        int deductResult = mathematics.getResult();
        Assert.assertEquals(deductResult, expectedDeduct, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.multiply(numberOne, numberTwo);
        int multiplyResult = mathematics.getResult();
        Assert.assertEquals(multiplyResult, expectedMultiply, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.divide(numberOne, numberTwo);
        int divideResult = mathematics.getResult();
        Assert.assertEquals(divideResult, expectedDivide, "Not expected result: ");
    }
}
