package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.OutputPlugin;

public class OutputPluginBundle {

    private final OutputPlugin plugin;
    private final Object pluginConfiguration;

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
