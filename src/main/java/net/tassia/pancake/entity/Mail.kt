package net.tassia.pancake.entity

import java.util.*

interface Mail : DatabaseEntity<Mail> {

	val mailID: UUID

	val isArchived: Boolean
	val isImportant: Boolean
	val isMarked: Boolean
	val isSpam: Boolean

	val deletionDate: Long?

}
