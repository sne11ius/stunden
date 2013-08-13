package nu.wasis.stunden;

import java.io.File;
import java.io.IOException;
import java.util.List;

import nu.wasis.stunden.cli.StundenOptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class Stunden {

    private static final Logger LOG = Logger.getLogger(Stunden.class);

    public static void main(final String[] args) throws ParseException, IOException {
        final CommandLineParser parser = new PosixParser();
        final CommandLine cmd = parser.parse(new StundenOptions(), args);

        if (!checkCommands(cmd)) {
            return;
        }

        for (final File file : new File(cmd.getOptionValue(StundenOptions.OPTION_DIRECTORY)).listFiles()) {
            LOG.info("Parsing file `" + file + "'.");
            final List<String> lines = FileUtils.readLines(file);
            for (final String line : lines) {
                LOG.debug(line);
            }
        }
    }

    private static boolean checkCommands(final CommandLine cmd) {
        if (!cmd.hasOption(StundenOptions.OPTION_DIRECTORY)) {
            LOG.error("Option `" + StundenOptions.OPTION_DIRECTORY + "' is not optional.");
            return false;
        }

        final File file = new File(cmd.getOptionValue(StundenOptions.OPTION_DIRECTORY));
        if (!file.isDirectory()) {
            LOG.error("Option `" + StundenOptions.OPTION_DIRECTORY + "' is not a directory.");
            return false;
        }
        return true;
    }
}
