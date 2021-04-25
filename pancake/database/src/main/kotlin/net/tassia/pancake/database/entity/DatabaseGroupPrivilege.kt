package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.GroupPrivilegeTable
import net.tassia.pancake.entity.GroupPrivilege
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
class DatabaseGroupPrivilege(id: EntityID<UUID>) : Entity<UUID>(id), GroupPrivilege {

	override val privilegeID: UUID
		get() = id.value

	override var groupID by GroupPrivilegeTable.groupID
	override var name by GroupPrivilegeTable.name
	override var value by GroupPrivilegeTable.value

	companion object : EntityClass<UUID, DatabaseGroupPrivilege>(GroupPrivilegeTable)

}
