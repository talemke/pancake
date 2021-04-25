package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface GroupPrivilege {

	val privilegeID: UUID

	var groupID: UUID
	var name: String
	var value: String

}
