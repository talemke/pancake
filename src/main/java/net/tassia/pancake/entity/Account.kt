package net.tassia.pancake.entity

import java.util.*

interface Account : DatabaseEntity<Account> {

	val accountID: UUID

	val displayName: String

	val inboxID: UUID
	val spamInboxID: UUID
	val trashInboxID: UUID

}
