package net.tassia.pancake.entity

import java.util.*

interface Route : Entity {

	val routeID: UUID

	var matchUsername: String
	var matchHostname: String

	var inboxID: UUID

}
