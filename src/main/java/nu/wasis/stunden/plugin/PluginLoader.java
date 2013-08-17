package nu.wasis.stunden.plugin;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import nu.wasis.stunden.config.InputPluginBundle;
import nu.wasis.stunden.config.OutputPluginBundle;
import nu.wasis.stunden.config.PluginConfig;
import nu.wasis.stunden.exception.InvalidConfigurationException;
import nu.wasis.stunden.util.JsonUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PluginLoader {

    public List<InputPluginBundle> readInputPlugins(final String jsonConfig) throws IOException {
        final List<InputPluginBundle> inputPluginBundles = new LinkedList<>();
        for (final JsonElement element : getInputConfigsElement(jsonConfig)) {
            final JsonObject inputConfig = element.getAsJsonObject();
            final String path = inputConfig.get("path").getAsString();
            final InputPlugin inputPlugin = loadInputPlugin(path);
            final Object pluginConfig = readInputPluginConfiguration(inputConfig, inputPlugin);
            final PluginConfig stundenPluginConfig = new PluginConfig(path, pluginConfig);
            inputPluginBundles.add(new InputPluginBundle(inputPlugin, stundenPluginConfig));
        }

        return inputPluginBundles;
    }

    public List<OutputPluginBundle> readOutputPlugins(final String jsonConfig) {
        final List<OutputPluginBundle> outputPluginBundles = new LinkedList<>();
        for (final JsonElement element : getOutputConfigsElement(jsonConfig)) {
            final JsonObject outputConfig = element.getAsJsonObject();
            final String path = outputConfig.get("path").getAsString();
            final OutputPlugin outputPlugin = loadOutputPlugin(path);
            final Object pluginConfig = readInputPluginConfiguration(outputConfig, outputPlugin);
            final PluginConfig stundenPluginConfig = new PluginConfig(path, pluginConfig);
            outputPluginBundles.add(new OutputPluginBundle(outputPlugin, stundenPluginConfig));
        }
        return outputPluginBundles;
    }

    private JsonArray getInputConfigsElement(final String jsonConfig) {
        return getJsonArray(jsonConfig, "inputConfigs");
    }

    private JsonArray getOutputConfigsElement(final String jsonConfig) {
        return getJsonArray(jsonConfig, "outputConfigs");
    }

    private JsonArray getJsonArray(final String jsonConfig, final String key) {
        final JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(jsonConfig).getAsJsonObject().get(key).getAsJsonArray();
    }

    private InputPlugin loadInputPlugin(final String path) {
        return (InputPlugin) loadPlugin(path, InputPlugin.class);
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
        return pluginManager.getPlugin(clazz);
    }

    private Object readInputPluginConfiguration(final JsonObject inputConfig, final Configurable configurable) {
        Object pluginConfiguration = null;
        final Class<?> configurationClass = configurable.getConfigurationClass();
        if (null != configurationClass) {
            final JsonElement jsonElement = inputConfig.get("config");
            if (!JsonNull.INSTANCE.equals(jsonElement)) {
                pluginConfiguration = JsonUtils.GSON.fromJson(jsonElement, configurationClass);
            }
        }
        return pluginConfiguration;
    }

}
