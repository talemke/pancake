package net.tassia.pancake.database.entity.table

import net.tassia.pancake.entity.SessionStatus
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Session]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object SessionTable : IdTable<UUID>("pancake_session") {

	override val id = entityId("SessionID", SessionTable)
	override val primaryKey = PrimaryKey(id)

	val token = varchar("SessionToken", 127).uniqueIndex()
	val status = enumeration("SessionStatus", SessionStatus::class)

	val accountID = uuid("AccountID").references(AccountTable.id)

}
