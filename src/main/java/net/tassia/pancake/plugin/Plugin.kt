package net.tassia.pancake.plugin

import net.tassia.pancake.Pancake
import net.tassia.pancake.logging.Level
import net.tassia.pancake.logging.Logger

/**
 * The super-class for all plugins.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
abstract class Plugin(open val pancake: Pancake) {

	/**
	 * The [PluginInfo] for this plugin.
	 */
	abstract val info: PluginInfo



	/**
	 * Invoked when the plugin should be loaded.
	 */
	open fun onLoad() = Unit

	/**
	 * Invoked when the plugin should be enabled.
	 */
	open fun onEnable() = Unit

	/**
	 * Invoked when the plugin should be disabled.
	 */
	open fun onDisable() = Unit



	/**
	 * Invoked when the plugin should be installed.
	 */
	open fun onInstall() = Unit

	/**
	 * Invoked when the plugin should be uninstalled.
	 */
	open fun onUninstall() = Unit



	/**
	 * Whether this plugin is currently enabled.
	 */
	fun isEnabled(): Boolean = pancake.plugins.isPluginEnabled(info)

	/**
	 * Whether this plugin is currently installed.
	 */
	fun isInstalled(): Boolean = pancake.plugins.isPluginInstalled(info)



	fun log(level: Level, message: String? = null, error: Throwable? = null) =
		Logger.log(this, level, message, error)

	fun info(message: String? = null, error: Throwable? = null) =
		Logger.info(message, error, this)

	fun debug(message: String? = null, error: Throwable? = null) =
		Logger.debug(message, error, this)

	fun warn(message: String? = null, error: Throwable? = null) =
		Logger.warn(message, error, this)

	fun error(message: String? = null, error: Throwable? = null) =
		Logger.error(message, error, this)

	fun severe(message: String? = null, error: Throwable? = null) =
		Logger.severe(message, error, this)

}
