package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface Session {

	val sessionID: UUID

	var token: String
	var status: SessionStatus

	var accountID: UUID

}
