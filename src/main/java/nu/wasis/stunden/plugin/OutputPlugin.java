package nu.wasis.stunden.plugin;

import net.xeoh.plugins.base.Plugin;
import nu.wasis.stunden.model.WorkPeriod;

/**
 * {@link OutputPlugin}s are supposed to generate some kind of output from a
 * given {@link WorkPeriod}.
 */
public interface OutputPlugin extends Plugin, Configurable {

	/**
	 * Output whatever the {@link OutputPlugin} finds suitable for the given
	 * {@link WorkPeriod}.
	 * 
	 * @param workPeriod The {@link WorkPeriod} to output.
	 * @param configuration The configuration for the {@link OutputPlugin}. The
	 *        concrete type of this param will be whatever the
	 *        {@link OutputPlugin#getConfigurationClass()} of the implementation
	 *        returns, but cannot be declared as such here since it's unknown.
	 */
    void output(WorkPeriod workPeriod, Object configuration);

    /**
     * See {@link Configurable#getConfigurationClass()}.
     */
    @Override
    Class<?> getConfigurationClass();

}
