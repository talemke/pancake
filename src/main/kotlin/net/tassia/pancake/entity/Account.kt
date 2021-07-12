package net.tassia.pancake.entity

import java.util.*

interface Account : Entity {

	val accountID: UUID

	var username: String
	var password: String

	var primaryGroupID: UUID

}
