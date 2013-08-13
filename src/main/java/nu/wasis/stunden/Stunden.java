package nu.wasis.stunden;

import java.io.IOException;

import nu.wasis.stunden.cli.StundenOptions;
import nu.wasis.stunden.config.StundenConfig;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

public class Stunden {

    private static final Logger LOG = Logger.getLogger(Stunden.class);

    public static void main(final String[] args) throws ParseException, IOException {
        final CommandLineParser parser = new PosixParser();
        final CommandLine cmd = parser.parse(new StundenOptions(), args);

        if (!checkCommands(cmd)) {
            return;
        }

        final StundenConfig config = StundenConfig.readConfig(cmd.getOptionValue(StundenOptions.OPTION_CONFIG_FILE));
        LOG.debug(config);
    }

    private static boolean checkCommands(final CommandLine cmd) {
        if (!cmd.hasOption(StundenOptions.OPTION_CONFIG_FILE)) {
            LOG.error("Option `" + StundenOptions.OPTION_CONFIG_FILE + "' is not optional.");
            return false;
        }

        return true;
    }
}
