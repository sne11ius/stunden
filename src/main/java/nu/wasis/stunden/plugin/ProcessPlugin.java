package nu.wasis.stunden.plugin;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

/**
 * {@link ProcessPlugin}s are supposed to transform a given {@link WorkPeriod}
 * in some way.
 */
public interface ProcessPlugin extends Plugin, Configurable {

	/**
	 * Process a given {@link WorkPeriod}.
	 * 
	 * @param workPeriod The {@link WorkPeriod} to process. Implementations may
	 *        choose to return the given, or a newly created {@link WorkPeriod}.
	 * @param configuration The configuration for this {@link ProcessPlugin}. The
	 *        concrete type of this param will be whatever the
	 *        {@link ProcessPlugin#getConfigurationClass()} of the
	 *        implementation returns, but cannot be declared as such here since
	 *        it's unknown.
	 * @return The result of the transformation of the given {@link WorkPeriod}.
	 *         Implementations may choose to return the given, or a newly
	 *         created {@link WorkPeriod}.
	 */
	WorkPeriod process(WorkPeriod workPeriod, Object configuration);
	
	/**
	 * See {@link Configurable#getConfigurationClass()}.
	 */
	@Override
    Class<?> getConfigurationClass();
}
