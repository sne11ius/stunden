package nu.wasis.stunden.cli;

import nu.wasis.stunden.Stunden;

import org.apache.commons.cli.Options;

/**
 * The commmand line {@link Options} for {@link Stunden}.
 * 
 * stunden currently has only one (required) option `c' which must point to the
 * configuration file.
 */
public class StundenOptions extends Options {

    private static final long serialVersionUID = 1L;

    /**
     * The only valid (and required) option for stunden. Must point to a
     * readable, valid json-File.
     */
    public static final String OPTION_CONFIG_FILE = "c";

    /**
     * Create a new {@link StundenOptions} object with pre-filled values.
     */
    public StundenOptions() {
        addOptions();
    }

    private void addOptions() {
        addOption(OPTION_CONFIG_FILE, true, "Configuration File.");
    }

}
