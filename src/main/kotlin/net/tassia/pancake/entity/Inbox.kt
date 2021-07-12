package net.tassia.pancake.entity

import java.util.*

interface Inbox : Entity {

	val inboxID: UUID

	var nameID: String
	var name: String

	var type: InboxType
	var sortingOrder: Int

	var accountID: UUID



	val isCustom: Boolean
		get() = type == InboxType.CUSTOM

}
