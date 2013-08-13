package nu.wasis.stunden;

import java.io.File;
import java.io.IOException;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import nu.wasis.stunden.cli.StundenOptions;
import nu.wasis.stunden.config.PluginConfig;
import nu.wasis.stunden.config.StundenConfig;
import nu.wasis.stunden.plugin.InputPlugin;

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

        final PluginManager pm = PluginManagerFactory.createPluginManager();

        for (final PluginConfig pluginConfig : config.getPluginConfigs()) {
            LOG.debug("Loading plugin from " + pluginConfig.getPath() + "...");
            pm.addPluginsFrom(new File(pluginConfig.getPath()).toURI());
            LOG.debug("... done");
        }

        final InputPlugin inputPlugin = pm.getPlugin(InputPlugin.class);
        if (null == inputPlugin) {
            throw new RuntimeException("Input plugin is null D:");
        }
        inputPlugin.read(null);
    }

    private static boolean checkCommands(final CommandLine cmd) {
        if (!cmd.hasOption(StundenOptions.OPTION_CONFIG_FILE)) {
            LOG.error("Option `" + StundenOptions.OPTION_CONFIG_FILE + "' is not optional.");
            return false;
        }

        return true;
    }
}
