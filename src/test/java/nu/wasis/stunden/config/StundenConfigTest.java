package nu.wasis.stunden.config;

import java.io.IOException;
import java.io.InputStreamReader;

import nu.wasis.stunden.util.JsonUtils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class StundenConfigTest {

    private static final Logger LOG = Logger.getLogger(StundenConfigTest.class);

    @Test
    public void testWriteStundenConfig() {
        final StundenConfig config = new StundenConfig();
        final String json = JsonUtils.GSON.toJson(config);
        LOG.debug(json);
    }

    @Test
    public void testReadStundenConfig() throws IOException {
        final InputStreamReader streamReader = new InputStreamReader(StundenConfigTest.class.getResourceAsStream("/default.json"));
        final String fileContent = IOUtils.toString(streamReader);
        JsonUtils.GSON.fromJson(fileContent, StundenConfig.class);
    }
}
