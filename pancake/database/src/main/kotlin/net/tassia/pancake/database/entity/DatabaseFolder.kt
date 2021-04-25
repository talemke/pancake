package net.tassia.pancake.database.entity

import net.tassia.pancake.database.entity.table.FolderTable
import net.tassia.pancake.entity.Folder
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * A folder is used to sort mails.
 *
 * @param id the ID
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class DatabaseFolder(id: EntityID<UUID>) : Entity<UUID>(id), Folder {

	override val folderID: UUID
		get() = id.value

	override var name by FolderTable.name
	override var description by FolderTable.description
	override var order by FolderTable.order
	override var type by FolderTable.type

	override var parentID by FolderTable.parentID
	override var accountID by FolderTable.accountID

	companion object : EntityClass<UUID, DatabaseFolder>(FolderTable)

}
