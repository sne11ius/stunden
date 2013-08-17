package nu.wasis.stunden.plugin;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

public interface InputPlugin extends Plugin, Configurable {

    WorkPeriod read(Object config);

    @Override
    Class<?> getConfigurationClass();

}
