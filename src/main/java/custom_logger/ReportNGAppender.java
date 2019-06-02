package custom_logger;

import org.apache.log4j.*;
import org.junit.rules.ExternalResource;

import java.io.ByteArrayOutputStream;

public class ReportNGAppender extends ExternalResource {
    private static final String APPENDER_NAME = "log4jReportNGAppender";
    private static final Layout LAYOUT = new SimpleLayout();

    private Logger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public ReportNGAppender(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void before() {
        Appender appender = new WriterAppender(LAYOUT, outContent);
        appender.setName(APPENDER_NAME);
        logger.addAppender(appender);
    }

    @Override
    protected void after() {
        logger.removeAppender(APPENDER_NAME);
    }

    public String getOutput() {
        return outContent.toString();
    }
}
