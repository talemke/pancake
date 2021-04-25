package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.GroupTable
import net.tassia.pancake.entity.Group
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
class DatabaseGroup(id: EntityID<UUID>) : Entity<UUID>(id), Group {

	override val groupID: UUID
		get() = id.value

	override var name by GroupTable.name
	override var description by GroupTable.description
	override var permissionOrder by GroupTable.permissionOrder
	override var sortingOrder by GroupTable.sortingOrder

	companion object : EntityClass<UUID, DatabaseGroup>(GroupTable)

}
