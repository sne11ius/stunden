package nu.wasis.stunden.model;

import org.joda.time.DateTime;

public class Entry {

    private final DateTime begin;
    private final DateTime end;
    private final Project project;

    public Entry(final DateTime begin, final DateTime end, final Project project) {
        this.begin = begin;
        this.end = end;
        this.project = project;
    }

    public DateTime getBegin() {
        return begin;
    }

    public DateTime getEnd() {
        return end;
    }

    public Project getProject() {
        return project;
    }

}
