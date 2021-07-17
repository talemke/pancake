package net.tassia.pancake.database.entity

import net.tassia.pancake.database.table.RouteTable
import net.tassia.pancake.entity.Route
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class RouteDB(id: EntityID<UUID>) : Entity<UUID>(id), Route {

	override val routeID: UUID
		get() = id.value

	override var matchUsername by RouteTable.matchUsername
	override var matchHostname by RouteTable.matchHostname

	override var inboxID by RouteTable.inboxID



	override fun reload() {
		this.refresh(false)
	}

	override fun update() {
		this.refresh(true)
	}



	companion object : EntityClass<UUID, RouteDB>(RouteTable)

}
