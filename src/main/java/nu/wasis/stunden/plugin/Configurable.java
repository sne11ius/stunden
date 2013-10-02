package nu.wasis.stunden.plugin;

/**
 * A configurable thing.
 */
public interface Configurable {

	/**
	 * Get the {@link Class} of the configuration.
	 * 
	 * @return The {@link Class} of the configuration.
	 */
    public Class<?> getConfigurationClass();

}
