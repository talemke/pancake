package net.tassia.pancake.entity.group

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * Represents a specific privilege/permission of a group.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class GroupPrivilege(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, GroupPrivilege>(GroupPrivilegeTable)

	var group by GroupPrivilegeTable.group
	var name by GroupPrivilegeTable.name
	var value by GroupPrivilegeTable.value

}
