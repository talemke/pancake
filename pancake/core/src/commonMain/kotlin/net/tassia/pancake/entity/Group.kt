package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface Group {

	val groupID: UUID

	val name: String
	val description: String?
	val permissionOrder: Int
	val sortingOrder: Int

}
