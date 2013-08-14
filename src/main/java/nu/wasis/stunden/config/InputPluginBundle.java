package nu.wasis.stunden.config;

import nu.wasis.stunden.plugin.InputPlugin;

public class InputPluginBundle {

    private final InputPlugin plugin;
    private final PluginConfig pluginConfig;

    public InputPluginBundle(final InputPlugin plugin, final PluginConfig pluginConfig) {
        this.plugin = plugin;
        this.pluginConfig = pluginConfig;
    }

    public InputPlugin getInputPlugin() {
        return plugin;
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

}
