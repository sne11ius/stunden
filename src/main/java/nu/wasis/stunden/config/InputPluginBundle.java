package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.InputPlugin;

/**
 * This class combines an {@link InputPlugin} with the corresponding plugin
 * configuration.
 */
public class InputPluginBundle {

    private final InputPlugin plugin;
    private final Object pluginConfiguration;

    /**
     * Create a new {@link InputPluginBundle}.
     * @param plugin The {@link InputPlugin}.
     * @param pluginConfiguration The configuration object for the
     *        {@link InputPlugin}. The type of the configuration object is
     *        {@link Object}, since the concrete class is defined by the plugin
     *        itself hence unknown to stunden.
     */
    public InputPluginBundle(final InputPlugin plugin, final Object pluginConfiguration) {
        this.plugin = plugin;
        this.pluginConfiguration = pluginConfiguration;
    }

    public InputPlugin getInputPlugin() {
        return plugin;
    }

    public Object getPluginConfiguration() {
        return pluginConfiguration;
    }

}
