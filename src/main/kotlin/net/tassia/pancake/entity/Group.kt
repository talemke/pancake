package net.tassia.pancake.entity

import java.util.*

interface Group : Entity {

	val groupID: UUID

	var name: String
	var description: String?

}
