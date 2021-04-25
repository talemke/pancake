package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.SessionTable
import net.tassia.pancake.entity.Session
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * A session is used to authenticate HTTP requests of an account.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class DatabaseSession(id: EntityID<UUID>) : Entity<UUID>(id), Session {

	override val sessionID: UUID
		get() = id.value

	override var token by SessionTable.token
	override var status by SessionTable.status

	override var accountID by SessionTable.accountID

	companion object : EntityClass<UUID, DatabaseSession>(SessionTable)

}
