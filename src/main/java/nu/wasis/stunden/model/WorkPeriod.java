package nu.wasis.stunden.model;

import java.util.SortedSet;
import java.util.TreeSet;

import nu.wasis.stunden.exception.NonUniqueDayException;
import nu.wasis.stunden.util.DateUtils;

import org.joda.time.DateTime;

/**
 * A work period like in `September 2013'.
 */
public class WorkPeriod {

    private final SortedSet<Day> days = new TreeSet<>();

    /**
     * Add all {@link Day}s from the given {@link WorkPeriod}.
     * 
     * @param workPeriod The {@link WorkPeriod} to add the days from.
     * @throws NonUniqueDayException a {@link Day} from the given
     *         {@link WorkPeriod} already exists in the current
     *         {@link WorkPeriod}.
     */
    public void addAll(final WorkPeriod workPeriod) {
        for (final Day day : workPeriod.getDays()) {
            if (days.contains(day)) {
                throw new NonUniqueDayException("Entry for date " + day.getDate().toString(DateUtils.DATE_FORMATTER) + " already exists.");
            }
            days.add(day);
        }
    }

    /**
     * Get the begin of this {@link WorkPeriod}.
     * 
     * @return The Date of the first day of this {@link WorkPeriod}.
     */
    public DateTime getBegin() {
    	if (days.isEmpty()) {
    		throw new IllegalStateException("This work period contains no days.");
    	}
        return days.first().getDate();
    }

    /**
     * Get the end of this {@link WorkPeriod}.
     * 
     * @return The Date of the last day of this {@link WorkPeriod}.
     */
    public DateTime getEnd() {
    	if (days.isEmpty()) {
    		throw new IllegalStateException("This work period contains no days.");
    	}
        return days.last().getDate();
    }

    public SortedSet<Day> getDays() {
        return days;
    }

}
