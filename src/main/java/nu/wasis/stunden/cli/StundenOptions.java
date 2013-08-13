package nu.wasis.stunden.cli;

import org.apache.commons.cli.Options;

public class StundenOptions extends Options {

    private static final long serialVersionUID = 1L;

    public static final String OPTION_CONFIG_FILE = "c";

    public StundenOptions() {
        addOptions();
    }

    private void addOptions() {
        addOption(OPTION_CONFIG_FILE, true, "Configuration File.");
    }

}
