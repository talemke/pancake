package net.tassia.pancake.entity

import java.util.*

interface Inbox : DatabaseEntity<Inbox> {

	val inboxID: UUID

	val customName: String?
	val defaultName: String?

}
