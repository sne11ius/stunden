package nu.wasis.stunden.model;

import java.util.List;

import org.joda.time.DateTime;

public class Day {

    private final DateTime date;
    private final List<Entry> entries;

    public Day(final DateTime date, final List<Entry> entries) {
        if (null == date) {
            throw new IllegalArgumentException("Param `date' must not be null.");
        }
        if (null == entries) {
            throw new IllegalArgumentException("Param `entries' must not be null.");
        }
        this.date = date;
        this.entries = entries;
    }

    public DateTime getDate() {
        return date;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Day other = (Day) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        return true;
    }

}
