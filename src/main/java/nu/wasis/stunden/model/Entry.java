package nu.wasis.stunden.model;

import java.util.Set;
import java.util.TreeSet;

import nu.wasis.stunden.util.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Entry implements Comparable<Entry> {

	private final DateTime begin;
    private DateTime end;
    private final Project project;
    private final Set<String> tags = new TreeSet<>();
    
    private boolean isBreak = false;

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
