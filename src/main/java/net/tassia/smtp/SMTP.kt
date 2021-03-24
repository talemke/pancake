package net.tassia.smtp

import net.tassia.pancake.InternetAddress

/**
 * The base class for the SMTP implementation.
 * Contains useful constants.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 */
object SMTP {

	/**
	 * A string consisting of the carriage return (CR) and line feed (LN) character.
	 *
	 * `<CR><LF>`
	 */
	const val CRLF = "\r\n"



	fun InternetAddress.toSMTPString(): String {
		return "${this.hostname} <$this>".trim()
	}

}
