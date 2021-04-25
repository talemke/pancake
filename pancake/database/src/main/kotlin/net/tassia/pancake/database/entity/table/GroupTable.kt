package net.tassia.pancake.database.entity.table

import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Group]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object GroupTable : IdTable<UUID>("pancake_group") {

	override val id = entityId("GroupID", GroupTable)
	override val primaryKey = PrimaryKey(id)

	val name = varchar("GroupName", 63).uniqueIndex()
	val description = text("Description").nullable()
	val permissionOrder = integer("PermissionOrder").default(0)
	val sortingOrder = integer("SortingOrder").default(0)

}
