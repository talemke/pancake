package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.AttachmentTable
import net.tassia.pancake.entity.Attachment
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
class DatabaseAttachment(id: EntityID<UUID>) : Entity<UUID>(id), Attachment {

	override val attachmentID: UUID
		get() = id.value

	override var contentLength by AttachmentTable.contentLength
	override var content by AttachmentTable.content

	override var fileName by AttachmentTable.fileName
	override var mimeType by AttachmentTable.mimeType

	override var mailID by AttachmentTable.mailID

	companion object : EntityClass<UUID, DatabaseAttachment>(AttachmentTable)

}
