package net.tassia.pancake.database.entity.table

import net.tassia.pancake.entity.RouteType
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Route]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object RouteTable : IdTable<UUID>("pancake_route") {

	override val id = entityId("RouteID", RouteTable)
	override val primaryKey = PrimaryKey(id)

	val value = varchar("RouteValue", 127)
	val type = enumeration("RouteType", RouteType::class)

	val targetID = uuid("RedirectTo").references(FolderTable.id)

}
