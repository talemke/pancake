package net.tassia.pancake.entity

import java.util.*

interface Mail : DatabaseEntity<Mail> {

	val mailID: UUID
	val transactionID: UUID

	val renderedHTML: String?

}
