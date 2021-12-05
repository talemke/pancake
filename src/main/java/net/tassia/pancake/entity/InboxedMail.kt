package net.tassia.pancake.entity

import java.util.*

interface InboxedMail : DatabaseEntity<InboxedMail> {

	val mailID: UUID
	val inboxID: UUID

	val isArchived: Boolean
	val isImportant: Boolean
	val isMarked: Boolean
	val isSpam: Boolean

	val deletionDate: Long?

}
