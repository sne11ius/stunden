package nu.wasis.stunden.config;

import java.util.Map;

public class PluginConfig {

    private String path;
    private Map<String, String> args;

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
