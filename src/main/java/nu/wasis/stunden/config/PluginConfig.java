package nu.wasis.stunden.config;

public class PluginConfig {

    private String path;
    private Object configuration;

    public PluginConfig() {
    }

    public PluginConfig(final String path, final Object configuration) {
        this.path = path;
        this.configuration = configuration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public Object getConfiguration() {
        return configuration;
    }

    public void setConfiguration(final Object configuration) {
        this.configuration = configuration;
    }

}
