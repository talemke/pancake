package net.tassia.pancake.entity

import java.util.*

interface MailTransaction : DatabaseEntity<MailTransaction> {

	val transactionID: UUID

	val transaction: TransactionLog

}
