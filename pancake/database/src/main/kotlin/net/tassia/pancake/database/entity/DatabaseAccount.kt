package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.AccountTable
import net.tassia.pancake.entity.Account
import net.tassia.pancake.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * Represents an account.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class DatabaseAccount(id: EntityID<UUID>) : Entity<UUID>(id), Account {

	override val accountID: UUID
		get() = id.value

	override var username by AccountTable.username
	override var password by AccountTable.password

	override var groupID by AccountTable.groupID

	companion object : EntityClass<UUID, DatabaseAccount>(AccountTable)

}
