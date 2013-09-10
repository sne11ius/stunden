package nu.wasis.stunden.plugin;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

public interface ProcessPlugin extends Plugin, Configurable {

	WorkPeriod process(WorkPeriod workPeriod, Object configuration);
	
	@Override
    Class<?> getConfigurationClass();
}
