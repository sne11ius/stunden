package nu.wasis.stunden.model;

import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Day implements Comparable<Day> {

	private final DateTime date;
    private final List<Entry> entries;

    public Day(final DateTime date, final List<Entry> entries) {
        if (null == date) {
            throw new IllegalArgumentException("Param `date' must not be null.");
        }
        if (null == entries) {
            throw new IllegalArgumentException("Param `entries' must not be null.");
        }
        this.date = new DateTime(date);
        this.entries = entries;
    }

    public DateTime getDate() {
        return new DateTime(date);
    }

    public List<Entry> getEntries() {
        return entries;
    }
    
    public boolean isTagged(final String tag) {
    	for (final Entry entry : entries) {
			if (entry.isTagged(tag)) {
				return true;
			}
		}
    	return false;
    }
    
    public boolean isTagged(final Collection<String> tags) {
    	for (final String tag : tags) {
			if (isTagged(tag)) {
				return true;
			}
		}
    	return false;
    }
    
    public Duration getWorkDuration() {
    	Duration duration = new Duration(0);
    	for (final Entry entry : entries) {
    		if (!entry.isBreak()) {
    			duration = duration.plus(entry.getDuration());
    		}
		}
    	return duration;
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

	@Override
	public int compareTo(final Day other) {
		return getDate().compareTo(other.getDate());
	}

    @Override
	public String toString() {
		return "Day [date=" + date + ", entries=" + entries + "]";
	}

}
