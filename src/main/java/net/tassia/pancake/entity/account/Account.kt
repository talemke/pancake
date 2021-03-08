package net.tassia.pancake.entity.account

import net.tassia.pancake.entity.group.Group
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * Represents an account.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Account(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Account>(AccountTable)

	var username by AccountTable.username
	var password by AccountTable.password

	var group by Group referencedOn AccountTable.group

}
