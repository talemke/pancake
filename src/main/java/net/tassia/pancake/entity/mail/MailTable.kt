package net.tassia.pancake.entity.mail

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

	val folder = reference("FolderID", FolderTable)

}
