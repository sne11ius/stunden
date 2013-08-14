package nu.wasis.stunden.plugin;

import java.util.Map;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

public interface OutputPlugin extends Plugin {

    void output(WorkPeriod workPeriod, Map<String, String> args);

}
