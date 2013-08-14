package nu.wasis.stunden.config;

import java.util.Map;

public class PluginConfig {

    private PluginDirection direction;
    private String path;
    private Map<String, String> args;

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

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(final Map<String, String> args) {
        this.args = args;
    }

}
