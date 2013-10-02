package nu.wasis.stunden.plugin;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import nu.wasis.stunden.config.InputPluginBundle;
import nu.wasis.stunden.config.OutputPluginBundle;
import nu.wasis.stunden.config.ProcessPluginBundle;
import nu.wasis.stunden.exception.InvalidConfigurationException;
import nu.wasis.stunden.util.JsonUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class can be used load Plugins for stunden.
 */
public class PluginLoader {

	/**
	 * Load all {@link InputPlugin}s declared in the given configuration. Also
	 * read the configuration for the plugins. Then bundle each loaded
	 * {@link InputPlugin} with its configuration object to create the
	 * {@link List} of {@link InputPluginBundle}s.
	 * 
	 * @param jsonConfig A JSON-{@link String}, containing a list of configured
	 *        {@link InputPlugin}s.
	 * @return A {@link List} of {@link InputPluginBundle}s, each containing the
	 *         {@link InputPlugin} bundled with the corresponding configuration.
	 */
    public List<InputPluginBundle> readInputPlugins(final String jsonConfig) {
        final List<InputPluginBundle> inputPluginBundles = new LinkedList<>();
        for (final JsonElement element : getInputConfigsElement(jsonConfig)) {
            final JsonObject inputConfig = element.getAsJsonObject();
            final String path = inputConfig.get("path").getAsString();
            final InputPlugin inputPlugin = loadInputPlugin(path);
            final Object pluginConfig = readPluginConfiguration(inputConfig, inputPlugin);
            inputPluginBundles.add(new InputPluginBundle(inputPlugin, pluginConfig));
        }

        return inputPluginBundles;
    }
    
    /**
     * Load all {@link ProcessPlugin}s declared in the given configuration. Also
	 * read the configuration for the plugins. Then bundle each loaded
	 * {@link ProcessPlugin} with its configuration object to create the
	 * {@link List} of {@link ProcessPluginBundle}s. 
     * @param jsonConfig A JSON-{@link String}, containing a list of configured
	 *        {@link ProcessPlugin}s.
	 * @return A {@link List} of {@link ProcessPluginBundle}s, each containing
	 *         the {@link ProcessPlugin} bundled with the corresponding
	 *         configuration.
     */
    public List<ProcessPluginBundle> readProcessPlugins(final String jsonConfig) {
        final List<ProcessPluginBundle> outputPluginBundles = new LinkedList<>();
        for (final JsonElement element : getProcessConfigsElement(jsonConfig)) {
            final JsonObject processConfig = element.getAsJsonObject();
            final String path = processConfig.get("path").getAsString();
            final ProcessPlugin processPlugin = loadProcessPlugin(path);
            final Object pluginConfiguration = readPluginConfiguration(processConfig, processPlugin);
            outputPluginBundles.add(new ProcessPluginBundle(processPlugin, pluginConfiguration));
        }
        return outputPluginBundles;
    }

    /**
     * Load all {@link OutputPlugin}s declared in the given configuration. Also
     * read the configuration for the plugins. Then bundle each loaded
     * {@link OutputPlugin} with its configuration object to create the
     * {@link List} of {@link OutputPluginBundle}s. 
     * @param jsonConfig A JSON-{@link String}, containing a list of configured
     *        {@link OutputPlugin}s.
     * @return A {@link List} of {@link OutputPluginBundle}s, each containing
     *         the {@link OutputPlugin} bundled with the corresponding
     *         configuration.
     */
	public List<OutputPluginBundle> readOutputPlugins(final String jsonConfig) {
        final List<OutputPluginBundle> outputPluginBundles = new LinkedList<>();
        for (final JsonElement element : getOutputConfigsElement(jsonConfig)) {
            final JsonObject outputConfig = element.getAsJsonObject();
            final String path = outputConfig.get("path").getAsString();
            final OutputPlugin outputPlugin = loadOutputPlugin(path);
            final Object pluginConfiguration = readPluginConfiguration(outputConfig, outputPlugin);
            outputPluginBundles.add(new OutputPluginBundle(outputPlugin, pluginConfiguration));
        }
        return outputPluginBundles;
    }

    private JsonArray getInputConfigsElement(final String jsonConfig) {
        return getJsonArray(jsonConfig, "inputConfigs");
    }
    
    private JsonArray getProcessConfigsElement(String jsonConfig) {
    	return getJsonArray(jsonConfig, "processConfigs");
    }

    private JsonArray getOutputConfigsElement(final String jsonConfig) {
        return getJsonArray(jsonConfig, "outputConfigs");
    }

    private JsonArray getJsonArray(final String jsonConfig, final String key) {
        final JsonParser jsonParser = new JsonParser();
        final JsonElement jsonElement = jsonParser.parse(jsonConfig).getAsJsonObject().get(key);
        JsonArray result = null;
        if (null == jsonElement) {
        	result = new JsonArray();
        } else {
			result = jsonElement.getAsJsonArray();
        }
        return result;
    }

    private InputPlugin loadInputPlugin(final String path) {
        return (InputPlugin) loadPlugin(path, InputPlugin.class);
    }

    private ProcessPlugin loadProcessPlugin(String path) {
        return (ProcessPlugin) loadPlugin(path, ProcessPlugin.class);
    }

    private OutputPlugin loadOutputPlugin(final String path) {
        return (OutputPlugin) loadPlugin(path, OutputPlugin.class);
    }

    private Plugin loadPlugin(final String path, final Class<? extends Plugin> clazz) {
        final PluginManager pluginManager = PluginManagerFactory.createPluginManager();
        final File pluginFile = new File(path);
        if (!pluginFile.exists()) {
            throw new InvalidConfigurationException("Plugin file does not exist @ " + path);
        }
        pluginManager.addPluginsFrom(new File(path).toURI());
        final Plugin plugin = pluginManager.getPlugin(clazz);
        if (null == plugin) {
            throw new InvalidConfigurationException("Unable to load Plugin from " + path);
        }
        return plugin;
    }
    
    private Object readPluginConfiguration(final JsonObject config, final Configurable configurable) {
    	if (null == config) {
            throw new IllegalArgumentException("Param `config' must not be null.");
        }
        if (null == configurable) {
            throw new IllegalArgumentException("Param `configurable' must not be null.");
        }
        Object pluginConfiguration = null;
        final Class<?> configurationClass = configurable.getConfigurationClass();
        if (null != configurationClass) {
            final JsonElement jsonElement = config.get("config");
            if (!JsonNull.INSTANCE.equals(jsonElement)) {
                pluginConfiguration = JsonUtils.GSON.fromJson(jsonElement, configurationClass);
            }
        }
        return pluginConfiguration;
    }

}
