package net.tassia.pancake.entity

import java.util.*

interface Privilege : Entity {

	val privilegeID: UUID

	var key: String
	var value: String

	var groupID: UUID

}
