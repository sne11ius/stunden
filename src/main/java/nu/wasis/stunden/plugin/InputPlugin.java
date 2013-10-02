package nu.wasis.stunden.plugin;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

/**
 * {@link InputPlugin}s are supposed to generate a {@link WorkPeriod} object
 * when run.
 */
public interface InputPlugin extends Plugin, Configurable {

	/**
	 * Read whatever this {@link InputPlugin} is supposed to read and create the
	 * resulting {@link WorkPeriod}.
	 * 
	 * @param configuration The configuration for this {@link InputPlugin}. The
	 *        concrete type of this param will be whatever the
	 *        {@link InputPlugin#getConfigurationClass()} of the implementation
	 *        returns, but cannot be declared as such here since it's unknown.
	 * @return
	 */
    WorkPeriod read(Object configuration);

    /**
     * See {@link Configurable#getConfigurationClass()}.
     */
    @Override
    Class<?> getConfigurationClass();

}
