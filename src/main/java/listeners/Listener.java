package listeners;

import custom_logger.CustomLogger;
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
import java.text.DateFormat;
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


    public static void makeScreenshot() {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotName = getName();
            String path = getPath(screenshotName);
            FileUtils.copyFile(screenshot, new File(path));
            CustomLogger.logScreen(screenshotName);

        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    private static String getName() {
//        DateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date(System.currentTimeMillis()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        Date date = new Date();
        return dateFormat.format(date) + "screenshot" + ".png";
    }

    private static String getPath(String name) {
        return "target\\surefire-reports\\html\\" + name;

    }
}

