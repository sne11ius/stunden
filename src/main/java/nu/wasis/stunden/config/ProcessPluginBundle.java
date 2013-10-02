package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.ProcessPlugin;

/**
 * This class combines an {@link ProcessPlugin} with the corresponding plugin
 * configuration.
 */
public class ProcessPluginBundle {

	private final ProcessPlugin plugin;
    private final Object pluginConfiguration;

    /**
     * Create a new {@link ProcessPluginBundle}.
     * @param plugin The {@link ProcessPlugin}.
     * @param pluginConfiguration The configuration object for the
     *        {@link ProcessPlugin}. The type of the configuration object is
     *        {@link Object}, since the concrete class is defined by the plugin
     *        itself hence unknown to stunden.
     */
    public ProcessPluginBundle(final ProcessPlugin plugin, final Object pluginConfiguration) {
        this.plugin = plugin;
        this.pluginConfiguration = pluginConfiguration;
    }

    public ProcessPlugin getProcessPlugin() {
        return plugin;
    }

    public Object getPluginConfiguration() {
        return pluginConfiguration;
    }

}
