package net.tassia.pancake.entity

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object AccountTable : IdTable<UUID>("PANCAKE_ACCOUNT") {

	override val id: Column<EntityID<UUID>> = uuid("AccountID").entityId()

	val username = varchar("Username", 127).uniqueIndex()
	val displayName = varchar("DisplayName", 127).nullable()
	val password = varchar("Password", 127).nullable()

	val isSuspended = bool("IsSuspended").default(false)

}
