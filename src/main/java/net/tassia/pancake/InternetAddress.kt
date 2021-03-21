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

) {

	override fun toString(): String = "$username@$hostname"

}
