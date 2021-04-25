package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface Attachment {

	val attachmentID: UUID

	var contentLength: Int
	var content: ByteArray?

	var fileName: String
	var mimeType: String

	var mailID: UUID

}
