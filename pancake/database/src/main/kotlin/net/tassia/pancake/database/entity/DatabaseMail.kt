package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.MailTable
import net.tassia.pancake.entity.Mail
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
class DatabaseMail(id: EntityID<UUID>) : Entity<UUID>(id), Mail {

	override val mailID: UUID
		get() = id.value

	override var contentLength by MailTable.contentLength
	override var content by MailTable.content

	override var timestamp by MailTable.timestamp
	override var subject by MailTable.subject
	override var sender by MailTable.sender
	override var recipients by MailTable.recipients

	override var folderID by MailTable.folderID

	companion object : EntityClass<UUID, DatabaseMail>(MailTable)

}
