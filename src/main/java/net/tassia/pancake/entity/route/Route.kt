package net.tassia.pancake.entity.route

import net.tassia.pancake.entity.folder.Folder
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
class Route(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Route>(RouteTable)

	var value by RouteTable.value
	var type by RouteTable.type

	var target by Folder referencedOn RouteTable.target

}
