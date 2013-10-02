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

    /**
     * Generates ISO dates like in <code>2013-02-03T20:33</code>.
     */
    public static final DateTimeFormatter DATE_FORMATTER = ISODateTimeFormat.date();
    
    /**
     * Generates dates in the form <code>03.02.13</code>.
     */
    public static final DateTimeFormatter DATE_FORMATTER_SHORT = new DateTimeFormatterBuilder()
    																	.appendDayOfMonth(2)
    																	.appendLiteral(".")
    																	.appendMonthOfYear(2)
    																	.appendLiteral(".")
    																	.appendYearOfCentury(0, 2)
    																	.toFormatter();
    /**
     * Generates times in the form <code>20:33</code>.
     */
    public static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
    															.appendClockhourOfDay(2)
    															.appendLiteral(":")
    															.appendMinuteOfHour(2)
    															.toFormatter();
    
    /**
     * Generates periods in the form <code>05:33</code>.
     */
    public static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()
    															.minimumPrintedDigits(2)
    															.printZeroAlways()
    															.appendHours()
    															.appendSeparator(":")
    															.appendMinutes()
    															.toFormatter();
}
