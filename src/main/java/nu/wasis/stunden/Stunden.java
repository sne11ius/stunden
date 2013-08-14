package nu.wasis.stunden;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import nu.wasis.stunden.cli.StundenOptions;
import nu.wasis.stunden.config.InputPluginBundle;
import nu.wasis.stunden.config.OutputPluginBundle;
import nu.wasis.stunden.config.PluginConfig;
import nu.wasis.stunden.config.StundenConfig;
import nu.wasis.stunden.model.WorkPeriod;
import nu.wasis.stunden.plugin.InputPlugin;
import nu.wasis.stunden.plugin.OutputPlugin;

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

        List<InputPluginBundle> inputPluginBundles = null;
        try {
            inputPluginBundles = loadInputPlugins(config.getInputConfigs());
        } catch (final InvalidConfigurationException e) {
            LOG.error("Error loading your damn input plugins. Fix your config, I'm outta here.", e);
            return;
        }

        final WorkPeriod combinedWorkPeriod = readCombinedWorkPeriod(inputPluginBundles);

        System.out.println("");

        List<OutputPluginBundle> outputPluginBundles = null;
        try {
            outputPluginBundles = loadOutputPlugins(config.getOutputConfigs());
        } catch (final InvalidConfigurationException e) {
            LOG.error("Error loading your damn output plugins. Fix your config, I'm outta here.", e);
            return;
        }

        for (final OutputPluginBundle outputPluginBundle : outputPluginBundles) {
            final OutputPlugin outputPlugin = outputPluginBundle.getOutputPlugin();
            try {
                final Map<String, String> pluginArgs = outputPluginBundle.getPluginConfig().getArgs();
                outputPlugin.output(combinedWorkPeriod, pluginArgs);
            } catch (final Exception e) {
                LOG.warn("Output plugin `" + outputPlugin.getClass().getName() + "' failed:", e);
            }
        }
    }

    // TODO: should use generic method, but that's to much of a hazzle in java - see below
    private static List<InputPluginBundle> loadInputPlugins(final List<PluginConfig> inputConfigs) {
        final List<InputPluginBundle> inputPlugins = new LinkedList<>();
        for (final PluginConfig pluginConfig : inputConfigs) {
            final PluginManager pm = PluginManagerFactory.createPluginManager();
            pm.addPluginsFrom(new File(pluginConfig.getPath()).toURI());
            final InputPlugin inputPlugin = pm.getPlugin(InputPlugin.class);
            if (null == inputPlugin) {
                throw new InvalidConfigurationException("No input plugin found @ " + pluginConfig.getPath());
            }
            inputPlugins.add(new InputPluginBundle(inputPlugin, pluginConfig));
        }
        return inputPlugins;
    }

    // TODO: should use generic method, but that's to much of a hazzle in java - see below
    private static List<OutputPluginBundle> loadOutputPlugins(final List<PluginConfig> outputConfigs) {
        final List<OutputPluginBundle> outputPlugins = new LinkedList<>();
        for (final PluginConfig pluginConfig : outputConfigs) {
            final PluginManager pm = PluginManagerFactory.createPluginManager();
            final File pluginFile = new File(pluginConfig.getPath());
            if (!pluginFile.exists()) {
                throw new InvalidConfigurationException("Plugin file does not exist @ " + pluginConfig.getPath());
            }
            pm.addPluginsFrom(pluginFile.toURI());
            final OutputPlugin outputPlugin = pm.getPlugin(OutputPlugin.class);
            if (null == outputPlugin) {
                throw new InvalidConfigurationException("No output plugin found @ " + pluginConfig.getPath());
            }
            outputPlugins.add(new OutputPluginBundle(outputPlugin, pluginConfig));
        }
        return outputPlugins;
    }

    private static WorkPeriod readCombinedWorkPeriod(final List<InputPluginBundle> inputPluginBundles) {
        final WorkPeriod combinedWorkPeriod = new WorkPeriod();
        for (final InputPluginBundle inputPluginBundle : inputPluginBundles) {
            final InputPlugin inputPlugin = inputPluginBundle.getInputPlugin();
            try {
                final Map<String, String> pluginArgs = inputPluginBundle.getPluginConfig().getArgs();
                final WorkPeriod workPeriod = inputPlugin.read(pluginArgs);
                combinedWorkPeriod.addAll(workPeriod);
            } catch (final Exception e) {
                LOG.warn("Input plugin `" + inputPlugin.getClass().getName() + "' failed:", e);
            }
        }
        return combinedWorkPeriod;
    }

    // Below:
    /*
     * private static <T1, T2 extends Plugin> List<T1> loadPlugins(final List<PluginConfig> configs, final Class<T2>
     * clazz) { final List<T1> plugins = new LinkedList<>(); for (final PluginConfig pluginConfig : configs) { final
     * PluginManager pm = PluginManagerFactory.createPluginManager(); pm.addPluginsFrom(new
     * File(pluginConfig.getPath()).toURI()); final T2 plugin = pm.getPlugin(clazz); plugins.add(new T1(pluginConfig,
     * plugin)); } return plugins; }
     */

    private static boolean checkCommands(final CommandLine cmd) {
        if (!cmd.hasOption(StundenOptions.OPTION_CONFIG_FILE)) {
            LOG.error("Option `" + StundenOptions.OPTION_CONFIG_FILE + "' is not optional.");
            return false;
        }

        return true;
    }
}
