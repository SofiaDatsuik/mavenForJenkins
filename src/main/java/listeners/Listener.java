package listeners;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import util.DriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements ITestListener {
    private static Logger LOG = Logger.getLogger(Listener.class.getName());

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOG.info("Teststarted running:" + iTestResult.getMethod().getMethodName()
                + " at:" + iTestResult.getStartMillis());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOG.info("Result success" + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        makeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
//
//    private byte[] makeScreenshot() {
//        return ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }

    public static void makeScreenshot() {
        File scrFile = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date(System.currentTimeMillis()));
            File screenhotName = new File("screenshots//screenshot"+ currentDate + ".png" );
            FileUtils.copyFile(scrFile, screenhotName);
            Reporter.log("<br><img src ='" + screenhotName + "'heigh ='400' weigh = '400'/><br>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

