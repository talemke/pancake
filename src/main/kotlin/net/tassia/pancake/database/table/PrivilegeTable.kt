package net.tassia.pancake.database.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object PrivilegeTable : IdTable<UUID>("pancake_privilege") {

	override val id: Column<EntityID<UUID>> = uuid("PrivilegeID").entityId()

	val key = varchar("PrivilegeKey", 127)
	val value = varchar("PrivilegeValue", 127)

	val groupID = uuid("GroupID")

	init {
		uniqueIndex(key, groupID)
	}

}
