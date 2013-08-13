package nu.wasis.stunden.plugin;

import java.util.Map;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

public interface InputPlugin extends Plugin {

    WorkPeriod read(Map<String, String> args);

}
