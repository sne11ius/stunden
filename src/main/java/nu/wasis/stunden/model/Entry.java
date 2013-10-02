package nu.wasis.stunden.model;

import java.util.Set;
import java.util.TreeSet;

import nu.wasis.stunden.util.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * A single occurence of work, like in `10:30 - 11:00: Meeting XY'.
 * <p>
 * Entries can be explicitly marked as break or tagged with arbitrary tags.
 * </p>
 */
public class Entry implements Comparable<Entry> {

	private final DateTime begin;
    private DateTime end;
    private final Project project;
    private final Set<String> tags = new TreeSet<>();
    
    private boolean isBreak = false;

    /**
     * Create a new {@link Entry} object. Note that copies will be created and
     * used for all params.
     * 
     * @param begin The begin of this {@link Entry}. Since {@link Entry}s are
     *        supposed to be used as parts of a {@link Day} object, the date
     *        (year, month, day) is supposed to be ignored, leaving only the
     *        time (hour, minute, ...).
     * @param end The end of this {@link Entry}. Since {@link Entry}s are
     *        supposed to be used as parts of a {@link Day} object, the date
     *        (year, month, day) is supposed to be ignored, leaving only the
     *        time (hour, minute, ...).
     * @param project The Project this entry belongs to.
     * @param isBreak Whether this {@link Entry} is regarded as break or work.
     */
    public Entry(final DateTime begin, final DateTime end, final Project project, final boolean isBreak) {
		this.begin = new DateTime(begin);
        this.end = new DateTime(end);
        this.project = new Project(project.getName());
        this.isBreak = isBreak;
    }
    
    /**
     * Create a new {@link Entry}, copying the values of the fiven entry. 
     * @param oldEntry The {@link Entry} to copy.
     */
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

    /**
     * Check if this {@link Entry} is tagged with the given tag. This check
     * ignores case.
     * @param tag The tag to check.
     * @return <code>true</code> if this {@link Entry} is tagged with the given
     *         tag. Else <code>false</code>.
     */
    public boolean isTagged(final String tag) {
    	for (final String knownTag : tags) {
    		if (knownTag.equalsIgnoreCase(tag)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Check if this {@link Entry} is tagged with any of the given tags. This
     * check ignores case.
     * @param externTags The tags to check.
     * @return <code>true</code> if this {@link Entry} is tagged with any of the
     *         given tags. Else <code>false</code>.
     */
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
