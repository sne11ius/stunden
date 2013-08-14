package nu.wasis.stunden.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import nu.wasis.stunden.util.JsonHelper;

public class StundenConfig {

    // private static final Logger LOG = Logger.getLogger(StundenConfig.class);

    private List<PluginConfig> inputConfigs = new LinkedList<PluginConfig>();
    private List<PluginConfig> outputConfigs = new LinkedList<PluginConfig>();

    public static StundenConfig readConfig(final String filename) throws IOException {

        // if (LOG.isDebugEnabled()) {
        // final String fileContent = IOUtils.toString(new FileReader(new File(filename)));
        // LOG.debug("Config file content:\n" + fileContent);
        // }

        return JsonHelper.GSON.fromJson(new FileReader(new File(filename)), StundenConfig.class);
    }

    public List<PluginConfig> getInputConfigs() {
        return inputConfigs;
    }

    public void setInputConfigs(final List<PluginConfig> inputConfigs) {
        this.inputConfigs = inputConfigs;
    }

    public List<PluginConfig> getOutputConfigs() {
        return outputConfigs;
    }

    public void setOutputConfigs(final List<PluginConfig> outputConfigs) {
        this.outputConfigs = outputConfigs;
    }

}
