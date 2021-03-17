package net.tassia.pancake.plugin

import net.tassia.pancake.Pancake

abstract class Plugin(open val pancake: Pancake) {

	abstract val info: PluginInfo



	abstract fun onEnable()
	abstract fun onDisable()

	abstract fun onInstall()
	abstract fun onUninstall()



	fun isEnabled(): Boolean = pancake.plugins.isPluginEnabled(info)
	fun isInstalled(): Boolean = pancake.plugins.isPluginInstalled(info)

}
