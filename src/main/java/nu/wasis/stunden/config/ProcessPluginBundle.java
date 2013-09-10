package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.ProcessPlugin;

public class ProcessPluginBundle {

	private final ProcessPlugin plugin;
    private final PluginConfig pluginConfig;

    public ProcessPluginBundle(final ProcessPlugin plugin, final PluginConfig pluginConfig) {
        this.plugin = plugin;
        this.pluginConfig = pluginConfig;
    }

    public ProcessPlugin getProcessPlugin() {
        return plugin;
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

}
