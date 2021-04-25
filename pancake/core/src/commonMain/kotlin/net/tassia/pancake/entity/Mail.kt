package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface Mail {

	val mailID: UUID

	var contentLength: Int
	var content: ByteArray?

	var timestamp: Long
	var subject: String?
	var sender: String
	var recipients: String

	var folderID: UUID

}
