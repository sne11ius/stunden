package nu.wasis.stunden.model;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

public class WorkPeriod {

    private DateTime begin = null;
    private DateTime end = null;

    private List<Day> days = new LinkedList<>();

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
