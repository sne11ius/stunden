package nu.wasis.stunden.plugin;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

public interface OutputPlugin extends Plugin, Configurable {

    void output(WorkPeriod workPeriod, Object configuration);

    @Override
    Class<?> getConfigurationClass();

}
