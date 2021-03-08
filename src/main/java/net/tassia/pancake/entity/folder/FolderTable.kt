package net.tassia.pancake.entity.folder

import net.tassia.pancake.entity.account.AccountTable
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Folder]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object FolderTable : IdTable<UUID>("pancake_folder") {

	override val id = entityId("FolderID", FolderTable)
	override val primaryKey = PrimaryKey(id)

	val name = varchar("FolderName", 63)
	val description = text("Description").nullable()
	val order = integer("SortingOrder").default(0)
	val type = enumeration("FolderType", FolderType::class)

	val parent = reference("ParentFolderID", FolderTable).nullable()
	val account = reference("AccountID", AccountTable)

}
