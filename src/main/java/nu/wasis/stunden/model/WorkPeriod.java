package nu.wasis.stunden.model;

import java.util.LinkedList;
import java.util.List;

import nu.wasis.stunden.exception.NonUniqueDayException;
import nu.wasis.stunden.util.DateUtils;

import org.joda.time.DateTime;

public class WorkPeriod {

    private DateTime begin = null;
    private DateTime end = null;

    private List<Day> days = new LinkedList<>();

    public WorkPeriod(final WorkPeriod workPeriod) {
    	this(workPeriod.getBegin(), workPeriod.getEnd());
    }
    
    public WorkPeriod(final DateTime begin, final DateTime end) {
        this.begin = new DateTime(begin);
        this.end = new DateTime(end);
    }

    public void addAll(final WorkPeriod workPeriod) {
        if (null == begin) {
            begin = workPeriod.getBegin();
        }
        if (null == end) {
            end = workPeriod.getEnd();
        }
        for (final Day day : workPeriod.getDays()) {
            if (day.getDate().isBefore(begin)) {
                begin = day.getDate();
            }
            if (day.getDate().isAfter(end)) {
                end = day.getDate();
            }
            if (days.contains(day)) {
                throw new NonUniqueDayException("Entry for date " + day.getDate().toString(DateUtils.DATE_FORMATTER) + " already exists.");
            }
            days.add(day);
        }
    }

    public DateTime getBegin() {
        return begin;
    }

    public void setBegin(final DateTime begin) {
        this.begin = begin;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(final DateTime end) {
        this.end = end;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(final List<Day> days) {
        this.days = days;
    }

}
