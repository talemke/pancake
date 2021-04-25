package net.tassia.pancake.database.entity.table

import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Account]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object AccountTable : IdTable<UUID>("pancake_account") {

	override val id = entityId("AccountID", AccountTable)
	override val primaryKey = PrimaryKey(id)

	val username = varchar("Username", 63).uniqueIndex()
	val password = varchar("Password", 127).nullable()

	val groupID = uuid("GroupID").references(GroupTable.id)

}
