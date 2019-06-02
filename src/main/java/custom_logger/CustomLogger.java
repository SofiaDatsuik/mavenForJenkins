package custom_logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.testng.Reporter;

import java.util.Date;


public class CustomLogger {

    private static final Logger LOG = LogManager.getLogger(CustomLogger.class);
//    private static boolean info = false;
//    private static boolean debug = false;
//    private static boolean error = false;
//    public void logError(Object msg) {
//        if(isError()) {
//            System.out.println(new Date().toString()+ " ERROR ["+LOG.className+"] - " + msg);
//        }
//    }


    public static void info(String log) {
        Reporter.log("<p style='color:yellow; background:black'>" + log + "</p>");
        LOG.info(log);
    }

    public static void debug(String log) {
        Reporter.log("<p style='color:blue; background:black'>" + log + "</p>");
        LOG.info(log);
    }

    public static void logScreen(String screenPath) {
        Reporter.log("<br>  <img src=" + screenPath + " height='500' width='800'/><br>");
    }
}
