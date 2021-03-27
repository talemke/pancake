package net.tassia.pancake

data class MimeMessage(

	val headers: MutableMap<String, String>,
	val body: String,

)
