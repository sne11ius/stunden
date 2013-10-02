package nu.wasis.stunden.model;

/**
 * A work project.
 */
public class Project {

	private final String name;

	/**
	 * Create a new {@link Project} with the given name.
	 * 
	 * @param name The name of this project.
	 */
    public Project(final String name) {
    	if (null == name) {
    		throw new IllegalArgumentException("Param `name' must not be null.");
    	}
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
	public String toString() {
		return "Project [name=" + name + "]";
	}

}
