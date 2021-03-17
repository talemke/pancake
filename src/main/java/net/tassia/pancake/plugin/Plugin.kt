package net.tassia.pancake.plugin

import net.tassia.event.Event
import net.tassia.pancake.Pancake
import kotlin.reflect.KClass

abstract class Plugin(open val pancake: Pancake) {

	abstract val info: PluginInfo
	open val events: Set<KClass<out Event>> = setOf()



	open fun onEnable() = Unit
	open fun onDisable() = Unit

	open fun onInstall() = Unit
	open fun onUninstall() = Unit



	fun isEnabled(): Boolean = pancake.plugins.isPluginEnabled(info)
	fun isInstalled(): Boolean = pancake.plugins.isPluginInstalled(info)

}
