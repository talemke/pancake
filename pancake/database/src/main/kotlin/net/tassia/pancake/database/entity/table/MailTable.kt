package net.tassia.pancake.database.entity.table

import net.tassia.pancake.Limits
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Mail]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object MailTable : IdTable<UUID>("pancake_mail") {

	override val id = entityId("MailID", MailTable)
	override val primaryKey = PrimaryKey(id)

	val contentLength = integer("ContentLength")
	val content = binary("Content", Limits.MAIL_CONTENT_MAX).nullable()

	val timestamp = long("Timestamp")
	val subject = varchar("Subject", Limits.MAIL_SUBJECT_MAX).nullable()
	val sender = varchar("Sender", Limits.MAIL_ADDRESS_MAX)
	val recipients = varchar("Recipients", (Limits.MAIL_ADDRESS_MAX + 1) * Limits.MAIL_RECIPIENTS_MAX)
	// TODO: CC, BCC

	val folderID = uuid("FolderID").references(FolderTable.id)

}
