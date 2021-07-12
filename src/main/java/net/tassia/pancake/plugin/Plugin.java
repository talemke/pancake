package net.tassia.pancake.plugin;

/**
 * Represents a base plugin. Plugins are used to add more functionality to Pancake on-demand.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public abstract class Plugin {

	/**
	 * Descriptive information about this plugin.
	 */
	public final PluginDescription description;



	/**
	 * Creates a new plugin with the given {@link PluginDescription}.
	 *
	 * @param description the description
	 */
	protected Plugin(PluginDescription description) {
		this.description = description;
	}



	/**
	 * Invoked when the plugin is loaded.
	 */
	public abstract void onLoad();

	/**
	 * Invoked when the plugin is enabled.
	 */
	public abstract void onEnable();

	/**
	 * Invoked when the plugin is disabled.
	 */
	public abstract void onDisable();



	/**
	 * Invoked when the plugin is installed.
	 *
	 * @implNote It is guaranteed, that when this method is invoked,
	 * 			an active database transaction is present.
	 */
	public abstract void onInstall();

	/**
	 * Invoked when the plugin is uninstalled.
	 *
	 * @implNote It is guaranteed, that when this method is invoked,
	 * 			an active database transaction is present.
	 */
	public abstract void onUninstall();

}
