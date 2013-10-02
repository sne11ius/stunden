package nu.wasis.stunden.model;

import java.util.SortedSet;
import java.util.TreeSet;

import nu.wasis.stunden.exception.NonUniqueDayException;
import nu.wasis.stunden.util.DateUtils;

import org.joda.time.DateTime;

public class WorkPeriod {

    private SortedSet<Day> days = new TreeSet<>();

    public void addAll(final WorkPeriod workPeriod) {
        for (final Day day : workPeriod.getDays()) {
            if (days.contains(day)) {
                throw new NonUniqueDayException("Entry for date " + day.getDate().toString(DateUtils.DATE_FORMATTER) + " already exists.");
            }
            days.add(day);
        }
    }

    public DateTime getBegin() {
    	if (days.isEmpty()) {
    		throw new IllegalStateException("This work period contains no days.");
    	}
        return days.first().getDate();
    }

    public DateTime getEnd() {
    	if (days.isEmpty()) {
    		throw new IllegalStateException("This work period contains no days.");
    	}
        return days.last().getDate();
    }

    public SortedSet<Day> getDays() {
        return days;
    }

    public void setDays(final SortedSet<Day> days) {
        this.days = days;
    }

}
