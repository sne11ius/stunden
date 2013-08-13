package nu.wasis.stunden.config;

public class PluginConfig {

    private PluginDirection direction;

    private String path;

    public PluginDirection getDirection() {
        return direction;
    }

    public void setDirection(final PluginDirection direction) {
        this.direction = direction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

}
