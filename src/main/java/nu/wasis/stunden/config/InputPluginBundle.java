package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.InputPlugin;

public class InputPluginBundle {

    private final InputPlugin plugin;
    private final Object pluginConfiguration;

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
