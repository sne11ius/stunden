package nu.wasis.stunden.model;

import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * A day full of work.
 */
public class Day implements Comparable<Day> {

	private final DateTime date;
    private final List<Entry> entries;

    /**
     * Create a new {@link Day} object.
     * @param date The date of this day. The exact time (hours, minutes etc.) of
     *        the used {@link DateTime} object are supposed to be ignored. Must
     *        not be <code>null</code>.
     * @param entries A {@link List} of {@link Entry} objects that occured on
     *        this day. Must not be <code>null</code>.
     */
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
    
    /**
     * Check if the {@link Day} is tagged with a tag.
     * @param tag The tag to check.
     * @return <code>true</code> if any of the {@link Entry}s of this day is
     *         tagged with the provided tag. Else <code>false</code>.
     */
    public boolean isTagged(final String tag) {
    	for (final Entry entry : entries) {
			if (entry.isTagged(tag)) {
				return true;
			}
		}
    	return false;
    }
    
    /**
     * Check if the {@link Day} is tagged with any of the provided tags.
     * @param tags A collection of tags to be checked.
     * @return <code>true</code> if any of the {@link Entry}s of this day is
     *         tagged with any of the provided tags. Else <code>false</code>.
     */
    public boolean isTagged(final Collection<String> tags) {
    	for (final String tag : tags) {
			if (isTagged(tag)) {
				return true;
			}
		}
    	return false;
    }
    
    /**
     * Get the total (billable) work duration for this {@link Day}.
     * 
     * @return The sum of the (billable) work durations of all {@link Entry}s of
     *         this day. {@link Entry}s are skipped if their
     *         <code>isBreak</code> method returns <code>true</code>.
     */
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
