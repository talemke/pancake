package net.tassia.pancake.entity.session

import net.tassia.pancake.entity.account.Account
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
class Session(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Session>(SessionTable)

	var token by SessionTable.token
	var status by SessionTable.status

	var account by Account referencedOn SessionTable.account

}
