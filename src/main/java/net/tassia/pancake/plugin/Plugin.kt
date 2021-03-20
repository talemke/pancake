package net.tassia.pancake.plugin

import net.tassia.event.Event
import net.tassia.pancake.Pancake
import kotlin.reflect.KClass

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

}
