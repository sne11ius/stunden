package nu.wasis.stunden.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Contains a pretty printing {@link Gson} instance.
 */
public class JsonUtils {

	/**
	 * A pretty printing {@link Gson} instance.
	 */
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

}
