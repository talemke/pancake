package net.tassia.pancake.database.entity.table

import net.tassia.pancake.Limits
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Attachment].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object AttachmentTable : IdTable<UUID>("pancake_attachment") {

	override val id = entityId("AttachmentID", AttachmentTable)
	override val primaryKey = PrimaryKey(id)

	val contentLength = integer("ContentLength")
	val content = binary("Content", Limits.MAIL_CONTENT_MAX).nullable()

	val fileName = varchar("FileName", 255)
	val mimeType = varchar("MimeType", 255)

	val mailID = uuid("MailID").references(MailTable.id)

}
