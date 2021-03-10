package net.tassia.pancake.entity.attachment

import net.tassia.pancake.entity.mail.Mail
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * Sometimes mails have files attached to them.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Attachment(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Attachment>(AttachmentTable)

	var contentLength by AttachmentTable.contentLength
	var content by AttachmentTable.content

	var fileName by AttachmentTable.fileName
	var mimeType by AttachmentTable.mimeType

	var mail by Mail referencedOn AttachmentTable.mail

}
