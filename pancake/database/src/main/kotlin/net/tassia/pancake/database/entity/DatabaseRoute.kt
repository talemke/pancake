package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.RouteTable
import net.tassia.pancake.entity.Route
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * A route is used to specify to which folder a mail should be redirected to.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class DatabaseRoute(id: EntityID<UUID>) : Entity<UUID>(id), Route {

	override val routeID: UUID
		get() = id.value

	override var value by RouteTable.value
	override var type by RouteTable.type

	override var targetID by RouteTable.targetID

	companion object : EntityClass<UUID, DatabaseRoute>(RouteTable)

}
