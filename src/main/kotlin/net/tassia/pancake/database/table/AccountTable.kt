package net.tassia.pancake.database.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object AccountTable : IdTable<UUID>("pancake_account") {

	override val id: Column<EntityID<UUID>> = uuid("AccountID").entityId()

	val username = varchar("Username", 63).uniqueIndex()
	val password = varchar("Password", 63).nullable()

	val primaryGroupID = uuid("PrimaryGroupID").index()

}
