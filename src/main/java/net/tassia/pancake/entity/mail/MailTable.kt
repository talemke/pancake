package net.tassia.pancake.entity.mail

import net.tassia.pancake.Pancake
import net.tassia.pancake.entity.folder.FolderTable
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
	val content = binary("Content", Pancake.MAIL_CONTENT_MAX).nullable()

	val timestamp = long("Timestamp")
	val subject = varchar("Subject", Pancake.MAIL_SUBJECT_MAX).nullable()
	val sender = varchar("Sender", Pancake.MAIL_ADDRESS_MAX)
	val recipients = varchar("Recipients", (Pancake.MAIL_ADDRESS_MAX + 1) * Pancake.MAIL_RECIPIENTS_MAX)
	// TODO: CC, BCC

	val folder = reference("FolderID", FolderTable)

}
