package net.tassia.pancake

import net.tassia.Version
import net.tassia.config.ini.IniLoadProvider
import net.tassia.pancake.database.Database
import net.tassia.pancake.plugin.PluginManager
import net.tassia.pancake.scheduler.Scheduler
import net.tassia.readResource
import net.tassia.toVersion
import java.util.logging.Logger

abstract class Pancake {

	abstract val database: Database
	abstract val logger: Logger
	abstract val pluginManager: PluginManager
	abstract val scheduler: Scheduler



	abstract fun shutdown()



	companion object {

		val VERSION: Version = readResource<Pancake>("/net/tassia/pancake/resources/pancake.ini").let {
			val config = IniLoadProvider.load(it.decodeToString())
			return@let config["Information.Version"] ?: "N/A"
		}.toVersion()

	}

}
