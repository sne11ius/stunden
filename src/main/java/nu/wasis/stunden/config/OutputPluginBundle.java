package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.OutputPlugin;

public class OutputPluginBundle {

    private final OutputPlugin plugin;
    private final PluginConfig pluginConfig;

    public OutputPluginBundle(final OutputPlugin plugin, final PluginConfig pluginConfig) {
        this.plugin = plugin;
        this.pluginConfig = pluginConfig;
    }

    public OutputPlugin getOutputPlugin() {
        return plugin;
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

}
