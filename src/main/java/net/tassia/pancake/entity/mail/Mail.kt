package net.tassia.pancake.entity.mail

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * A mail. The thing you send around. The thing this is all about.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Mail(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Mail>(MailTable)

}
