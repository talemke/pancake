package net.tassia.pancake.entity.group

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * A group specifies what permissions a user (Account) has.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Group(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Group>(GroupTable)

	var name by GroupTable.name
	var description by GroupTable.description
	var permissionOrder by GroupTable.permissionOrder
	var sortingOrder by GroupTable.sortingOrder

}
