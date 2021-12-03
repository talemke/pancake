package net.tassia.pancake

import net.tassia.Version
import net.tassia.decodeToVersion
import net.tassia.ini.readIniDocument
import net.tassia.pancake.database.Database
import net.tassia.pancake.plugin.PluginManager
import net.tassia.pancake.scheduler.Scheduler
import net.tassia.pancake.util.readResource
import net.tassia.parser.StringParser
import java.util.logging.Logger

abstract class Pancake {

	abstract val database: Database
	abstract val logger: Logger
	abstract val pluginManager: PluginManager
	abstract val scheduler: Scheduler



	companion object {

		val VERSION: Version = readResource<Pancake>("/net/tassia/pancake/resources/pancake.ini").let {
			val ini = StringParser(it).readIniDocument()
			return@let ini["Information.Version"] ?: "N/A"
		}.decodeToVersion()

	}

}
