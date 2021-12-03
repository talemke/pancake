package net.tassia.pancake

import net.tassia.pancake.database.StandardDatabase
import net.tassia.pancake.event.EventManager
import net.tassia.pancake.plugin.StandardPluginManager
import net.tassia.pancake.scheduler.StandardScheduler
import java.util.logging.Logger
import javax.sql.DataSource

class StandardPancake(override val logger: Logger, source: DataSource) : Pancake() {

	override val database = StandardDatabase(source)
	val eventManager = EventManager()
	override val scheduler = StandardScheduler()
	override val pluginManager = StandardPluginManager(this)



	fun launch() {
		TODO()
	}

}
