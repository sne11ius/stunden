package nu.wasis.stunden.cli;

import org.apache.commons.cli.Options;

public class StundenOptions extends Options {

    private static final long serialVersionUID = 1L;

    public static final String OPTION_CHECK = "c";
    public static final String OPTION_DIRECTORY = "d";

    public StundenOptions() {
        addOptions();
    }

    private void addOptions() {
        addOption(OPTION_CHECK, true, "Only check files.");
        addOption(OPTION_DIRECTORY, true, "Source directory.");
    }

}
