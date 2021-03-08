package net.tassia.pancake.entity.folder

import net.tassia.pancake.entity.account.Account
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
class Folder(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Folder>(FolderTable)

	var name by FolderTable.name
	var description by FolderTable.description
	var order by FolderTable.order
	var type by FolderTable.type

	var parent by Folder optionalReferencedOn FolderTable.parent
	var account by Account referencedOn FolderTable.account

}
