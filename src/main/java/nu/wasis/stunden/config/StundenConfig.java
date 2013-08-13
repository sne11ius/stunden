package nu.wasis.stunden.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import nu.wasis.stunden.util.JsonHelper;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class StundenConfig {

    private static final Logger LOG = Logger.getLogger(StundenConfig.class);

    private List<PluginConfig> pluginConfigs = new LinkedList<PluginConfig>();

    public static StundenConfig readConfig(final String filename) throws IOException {
        if (LOG.isDebugEnabled()) {
            final String fileContent = IOUtils.toString(new FileReader(new File(filename)));
            LOG.debug("Config file content:\n" + fileContent);
        }
        return JsonHelper.GSON.fromJson(new FileReader(new File(filename)), StundenConfig.class);
    }

    public List<PluginConfig> getPluginConfigs() {
        return pluginConfigs;
    }

    public void setPluginConfigs(final List<PluginConfig> pluginConfigs) {
        this.pluginConfigs = pluginConfigs;
    }
}
