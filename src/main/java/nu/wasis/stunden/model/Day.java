package nu.wasis.stunden.model;

import java.util.List;

import org.joda.time.DateTime;

public class Day {

    private final DateTime date;
    private final List<Entry> entries;

    public Day(final DateTime date, final List<Entry> entries) {
        this.date = date;
        this.entries = entries;
    }

    public DateTime getDate() {
        return date;
    }

    public List<Entry> getEntries() {
        return entries;
    }

}
