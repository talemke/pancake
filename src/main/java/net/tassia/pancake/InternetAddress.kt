package net.tassia.pancake

/**
 * Represents an internet address aka. an email address.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class InternetAddress(

	/**
	 * The username of this address.
	 */
	val username: String,

	/**
	 * The hostname of this address.
	 */
	val hostname: String,

	/**
	 * The full name associated with this address.
	 */
	val fullName: String?,

) {

	override fun toString(): String = "$username@$hostname"



	companion object {

		/**
		 * Parses the given string into an [InternetAddress].
		 *
		 * @param str the string to parse
		 * @return the parsed address, or `null` on failure
		 */
		fun parse(str: String): InternetAddress? {
			if (!str.contains('@')) return null
			val username = str.split('@', limit = 2)[0]
			val hostname = str.substring(username.length + 1)
			return InternetAddress(username, hostname, null)
		}

	}

}
