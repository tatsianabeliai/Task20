import com.sun.org.glassfish.gmbal.Description;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class ClassOneTest extends TxtDataProvider {
    private static final Logger LOG = Logger.getLogger(ClassOneTest.class);
    private Mathematics mathematics;
    private final static int numberOne = 50;
    private final static int numberTwo = 5;
    private final static int sumResult = 55;
    private final static int deductResult = 45;
    private final static int multiplyResult = 250;
    private final static int divideResult = 10;

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
        mathematics.add(numberOne, numberTwo);
        int result = mathematics.getResult();
        Assert.assertEquals(sumResult, result, "Not expected result is displayed");
    }

    @Test(priority = 3, timeOut = 500, groups = {"fast"}, dependsOnMethods = "addTest")
    public void deductTest() throws InterruptedException {
        Thread.sleep(1500);
        mathematics.deduct(numberOne, numberTwo);
        int result = mathematics.getResult();
        Assert.assertEquals(deductResult, result, "Not expected result is displayed");
    }

    @Test(priority = 4, groups = {"fast"})
    public void multiplyTest() {
        mathematics.multiply(numberOne, numberTwo);
        int result = mathematics.getResult();
        Assert.assertEquals(multiplyResult, result, "Not expected result is displayed");
    }

    @Test(priority = 2, groups = {"fast"})
    public void divideTest() {
        mathematics.divide(numberOne, numberTwo);
        int result = mathematics.getResult();
        Assert.assertEquals(divideResult, result, "Not expected result is displayed");
    }

    @Test(priority = 1, groups = {"fast"})
    public void divideByZeroTest() {
        mathematics.divide(numberOne, 0);
        int result = mathematics.getResult();
        Assert.assertEquals(divideResult, result);
    }

    @Description("The test gets data from testngDDTTest.xml file. Run testngGGTTest.xml")
    @Test(groups = "fast")
    @Parameters(value = {"numberX", "numberY"})
    public void DDTTest(int numberX, int numberY) {
        mathematics.add(numberX, numberY);
        Assert.assertEquals(mathematics.getResult(), numberX + numberY, "Not expected result: ");

        mathematics.deduct(numberX, numberY);
        Assert.assertEquals(mathematics.getResult(), numberX - numberY, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.multiply(numberX, numberY);
        Assert.assertEquals(mathematics.getResult(), numberX * numberY, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.divide(numberX, numberY);
        Assert.assertEquals(mathematics.getResult(), numberX / numberY, "Not expected result: ");
    }

    @Description("The test gets data from testData.txt file.")
    @Test(groups = "fast", dataProvider = "txtDataProvider")
    public void DDTWithTxtDataProviderTest(int numberOne, int numberTwo) {
        mathematics.add(numberOne, numberTwo);
        Assert.assertEquals(mathematics.getResult(), numberOne + numberTwo, "Not expected result: ");

        mathematics.deduct(numberOne, numberTwo);
        Assert.assertEquals(mathematics.getResult(), numberOne - numberTwo, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.multiply(numberOne, numberTwo);
        Assert.assertEquals(mathematics.getResult(), numberOne * numberTwo, "Not expected result: ");

        mathematics.setResult(0);
        mathematics.divide(numberOne, numberTwo);
        Assert.assertEquals(mathematics.getResult(), numberOne / numberTwo, "Not expected result: ");
    }
}
