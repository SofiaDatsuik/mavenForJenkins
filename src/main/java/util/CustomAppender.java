package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class CustomAppender extends AppenderSkeleton {

    public CustomAppender() {
        this.name = "CustomAppender";
    }

    private final List<LoggingEvent> log = new ArrayList<LoggingEvent>();

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(LoggingEvent event) {
        log.add(event);
    }

    public List<LoggingEvent> getLog() {
        return new ArrayList<LoggingEvent>(log);
    }

    public String getLogAsString() {
        String dataOut = "--- [Detailed Test Output] ---\n";
        Iterator<LoggingEvent> it = log.iterator();
        while (it.hasNext()) {
            LoggingEvent event = it.next();
            dataOut = dataOut + event.getRenderedMessage() + "\n";
        }
        dataOut = dataOut + "--- [END] ---";
        return dataOut;
    }
}