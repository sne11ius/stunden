package nu.wasis.stunden.util;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class DateUtils {

    private DateUtils() {
        // static only
    }

    public static final DateTimeFormatter DATE_FORMATTER = ISODateTimeFormat.date();
    public static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
    															.appendClockhourOfDay(2)
    															.appendLiteral(":")
    															.appendMinuteOfHour(2)
    															.toFormatter();
    public static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()
    															.minimumPrintedDigits(2)
    															.printZeroAlways()
    															.appendHours()
    															.appendSeparator(":")
    															.appendMinutes()
    															.toFormatter();
}
