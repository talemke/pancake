package net.tassia.pancake;

/**
 * Contains a few static resource paths.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class Resources {

	/**
	 * The base path, prefixed by all resources.
	 */
	private static final String BASE = "/net/tassia/pancake/resources";



	/**
	 * The configuration file.
	 */
	public static final String CONFIG = BASE + "/config/config.ini";



	/**
	 * The installation data file.
	 */
	public static final String INSTALLATION_DATA = BASE + "/data/installation.properties";



	/**
	 * The version information file.
	 */
	public static final String VERSION = BASE + "/internal/version.properties";



	/**
	 * Static class.
	 */
	private Resources() {
	}

}
