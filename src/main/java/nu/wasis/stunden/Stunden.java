package nu.wasis.stunden;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import nu.wasis.stunden.cli.StundenOptions;
import nu.wasis.stunden.config.InputPluginBundle;
import nu.wasis.stunden.config.OutputPluginBundle;
import nu.wasis.stunden.config.PluginConfig;
import nu.wasis.stunden.config.ProcessPluginBundle;
import nu.wasis.stunden.exception.InvalidConfigurationException;
import nu.wasis.stunden.model.WorkPeriod;
import nu.wasis.stunden.plugin.InputPlugin;
import nu.wasis.stunden.plugin.OutputPlugin;
import nu.wasis.stunden.plugin.PluginLoader;
import nu.wasis.stunden.plugin.ProcessPlugin;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class Stunden {

    private static final Logger LOG = Logger.getLogger(Stunden.class);

    public static void main(final String[] args) throws ParseException, IOException {
        final CommandLineParser parser = new PosixParser();
        final CommandLine cmd = parser.parse(new StundenOptions(), args);

        if (!checkCommands(cmd)) {
            return;
        }

        final InputStreamReader streamReader = new FileReader(new File(cmd.getOptionValue(StundenOptions.OPTION_CONFIG_FILE)));
        final String fileContent = IOUtils.toString(streamReader);

        WorkPeriod combinedWorkPeriod = runInputPlugins(fileContent);
        if (null == combinedWorkPeriod) {
        	LOG.error("Error running the input plugins. Quitting.");
        	return;
        }
        combinedWorkPeriod = runProcessPlugins(combinedWorkPeriod, fileContent);
        if (null == combinedWorkPeriod) {
        	LOG.error("Error running the process plugins. Quitting.");
        	return;
        }

        runOutputPlugins(fileContent, combinedWorkPeriod);
    }
    
    private static WorkPeriod runInputPlugins(final String fileContent) throws IOException {
    	// Load input plugins
        List<InputPluginBundle> inputPluginBundles = null;
        try {
            inputPluginBundles = new PluginLoader().readInputPlugins(fileContent);
        } catch (final InvalidConfigurationException e) {
            LOG.error("Error loading your damn input plugins. Fix your config, I'm outta here.", e);
            return null;
        }

        // Run input plugins
        WorkPeriod combinedWorkPeriod = null;
        try {
            combinedWorkPeriod = readCombinedWorkPeriod(inputPluginBundles);
        } catch (final Exception e) {
            LOG.error("Error while reading input:", e);
            return null;
        }
        if (null == combinedWorkPeriod || combinedWorkPeriod.getDays().isEmpty()) {
            LOG.warn("No input plugin generated any data. I'm outta here.");
            return null;
        }
        return combinedWorkPeriod;
    }
    
    private static WorkPeriod runProcessPlugins(WorkPeriod combinedWorkPeriod, final String fileContent) {
    	// Load process plugins
        List<ProcessPluginBundle> processPluginBundles = null;
        try {
        	processPluginBundles = new PluginLoader().readProcessPlugins(fileContent);
        } catch (final InvalidConfigurationException e) {
            LOG.error("Error loading your damn process plugins. Fix your config, I'm outta here.", e);
            return null;
        }
        
        // Run process plugins
        for (final ProcessPluginBundle processPluginBundle : processPluginBundles) {
			final ProcessPlugin processPlugin = processPluginBundle.getProcessPlugin();
			final PluginConfig pluginConfig = processPluginBundle.getPluginConfig();
			LOG.info("Processing via `" + processPlugin.getClass().getName() + "'...");
			try {
				combinedWorkPeriod = processPlugin.process(combinedWorkPeriod, pluginConfig.getConfiguration());
			} catch (final Exception e) {
				LOG.error("Error processing via " + processPlugin.getClass().getName() + ": ", e);
				return null;
			}
			LOG.info("...done.");
		}
        return combinedWorkPeriod;
    }
    
    private static void runOutputPlugins(final String fileContent, final WorkPeriod combinedWorkPeriod) {
    	// Load output plugins
        List<OutputPluginBundle> outputPluginBundles = null;
        try {
            outputPluginBundles = new PluginLoader().readOutputPlugins(fileContent);
        } catch (final InvalidConfigurationException e) {
            LOG.error("Error loading your damn output plugins. Fix your config, I'm outta here.", e);
            return;
        }

        // Run output plugins
        for (final OutputPluginBundle outputPluginBundle : outputPluginBundles) {
            final OutputPlugin outputPlugin = outputPluginBundle.getOutputPlugin();
            try {
                final Object pluginConfig = outputPluginBundle.getPluginConfig().getConfiguration();
                LOG.info("Outputting via `" + outputPlugin.getClass().getName() + "'...");
                outputPlugin.output(combinedWorkPeriod, pluginConfig);
                LOG.info("... done.");
            } catch (final Exception e) {
                LOG.warn("Output plugin `" + outputPlugin.getClass().getName() + "' failed:", e);
            }
        }
    }

    private static WorkPeriod readCombinedWorkPeriod(final List<InputPluginBundle> inputPluginBundles) {
        WorkPeriod combinedWorkPeriod = null;
        for (final InputPluginBundle inputPluginBundle : inputPluginBundles) {
            final InputPlugin inputPlugin = inputPluginBundle.getInputPlugin();
            final Object pluginConfig = inputPluginBundle.getPluginConfig().getConfiguration();
            LOG.info("Reading via `" + inputPlugin.getClass().getName() + "'...");
            final WorkPeriod workPeriod = inputPlugin.read(pluginConfig);
            if (null == combinedWorkPeriod) {
                combinedWorkPeriod = workPeriod;
            } else {
                combinedWorkPeriod.addAll(workPeriod);
            }
            LOG.info("... done.");
        }
        return combinedWorkPeriod;
    }

    /**
     * Check the commands.
     * @param cmd The {@link CommandLine} generated from <code>args</code>.
     * @return <code>true</code>, wenn cmd alle benötigten Parameter enthält. Sonst <code>false</code>.
     */
    private static boolean checkCommands(final CommandLine cmd) {
        if (!cmd.hasOption(StundenOptions.OPTION_CONFIG_FILE)) {
            LOG.error("Option `" + StundenOptions.OPTION_CONFIG_FILE + "' is not optional.");
            return false;
        }

        return true;
    }
}
