package nu.wasis.stunden.model;

import java.util.Set;
import java.util.TreeSet;

import nu.wasis.stunden.util.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * A single occurence of work. Like in `10:30 - 11:00: Meeting XY'.
 * 
 * Entries can be explicitly marked as break or tagged with arbitrary tags.
 */
public class Entry implements Comparable<Entry> {

	private final DateTime begin;
    private DateTime end;
    private final Project project;
    private final Set<String> tags = new TreeSet<>();
    
    private boolean isBreak = false;

    /**
     * Create a new {@link Entry} object.
     * 
     * @param begin The begin of this {@link Entry}. Since {@link Entry}s are
     *        supposed to be used as parts of a {@link Day} object, the date
     *        (year, month, day) is supposed to be ignored, leaving only the
     *        time (hour, minute, ...).
     * @param end The end of this {@link Entry}. Since {@link Entry}s are
     *        supposed to be used as parts of a {@link Day} object, the date
     *        (year, month, day) is supposed to be ignored, leaving only the
     *        time (hour, minute, ...).
     * @param project
     * @param isBreak
     */
    public Entry(final DateTime begin, final DateTime end, final Project project, final boolean isBreak) {
		this.begin = new DateTime(begin);
        this.end = new DateTime(end);
        this.project = new Project(project.getName());
        this.isBreak = isBreak;
    }
    
    public Entry(final Entry oldEntry) {
    	this(oldEntry.getBegin(), oldEntry.getEnd(), oldEntry.getProject(), oldEntry.isBreak());
    }

    public DateTime getBegin() {
        return new DateTime(begin);
    }

    public DateTime getEnd() {
        return new DateTime(end);
    }
    
    public void setEnd(final DateTime end) {
    	this.end = new DateTime(end);
    }

    public Project getProject() {
        return project;
    }
    
    public boolean isBreak() {
    	return isBreak;
    }
    
    public void setBreak(final boolean isBreak) {
    	this.isBreak = isBreak;
    }
    
    public Set<String> getTags() {
    	return tags;
    }
    
    public Duration getDuration() {
    	return new Duration(begin, end);
    }

    public boolean isTagged(final String tag) {
    	for (final String knownTag : tags) {
    		if (knownTag.equalsIgnoreCase(tag)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isTagged(Set<String> externTags) {
    	for (String externTag : externTags) {
			if (isTagged(externTag)) {
				return true;
			}
		}
    	return false;
    }
    
	@Override
	public int compareTo(final Entry other) {
		return begin.compareTo(other.getBegin());
	}

	@Override
	public String toString() {
		return "Entry [begin=" + DateUtils.TIME_FORMATTER.print(begin) + ", end=" + DateUtils.TIME_FORMATTER.print(end) + ", project=" + project + ", isBreak=" + isBreak + ", tags=" + tags + "]";
	}

}
