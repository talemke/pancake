package net.tassia.pancake.database.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object GroupTable : IdTable<UUID>("pancake_group") {

	override val id: Column<EntityID<UUID>> = uuid("GroupID").entityId()

	val name = varchar("GroupName", 63)
	val description = text("Description").nullable()

}
