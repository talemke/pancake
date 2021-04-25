package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface Folder {

	val folderID: UUID

	var name: String
	var description: String?
	var order: Int
	var type: FolderType

	var parentID: UUID?
	var accountID: UUID

}
