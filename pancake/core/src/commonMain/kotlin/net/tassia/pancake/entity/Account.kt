package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface Account {

	val accountID: UUID

	var username: String
	var password: String?

	var groupID: UUID

}
