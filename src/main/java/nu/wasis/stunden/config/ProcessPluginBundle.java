package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.ProcessPlugin;

public class ProcessPluginBundle {

	private final ProcessPlugin plugin;
    private final Object pluginConfiguration;

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
