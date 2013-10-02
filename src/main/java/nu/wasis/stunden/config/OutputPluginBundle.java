package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.OutputPlugin;

/**
 * This class bundles an {@link OutputPlugin} with the corresponding
 * configuration object.
 */
public class OutputPluginBundle {

    private final OutputPlugin plugin;
    private final Object pluginConfiguration;

    /**
     * Create a new {@link OutputPluginBundle}.
     * @param plugin The {@link OutputPlugin}.
     * @param pluginConfiguration The configuration object for the
     *        {@link OutputPlugin}. The type of the configuration object is
     *        {@link Object}, since the concrete class is defined by the plugin
     *        itself hence unknown to stunden.
     */
    public OutputPluginBundle(final OutputPlugin plugin, final Object pluginConfiguration) {
        this.plugin = plugin;
        this.pluginConfiguration = pluginConfiguration;
    }

    public OutputPlugin getOutputPlugin() {
        return plugin;
    }

    public Object getPluginConfiguration() {
        return pluginConfiguration;
    }

}
