package net.tassia.smtp

import java.io.IOException

/**
 * Represents an SMTP command.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 */
abstract class SmtpCommand {

	/**
	 * A set of valid response codes for this command.
	 * If the server sends a response not included in
	 * this set, the connection will be closed.
	 */
	abstract val responses: Set<Int>

	/**
	 * Writes this command to the given connection.
	 *
	 * @param connection the connection
	 *
	 * @throws IOException if an I/O error occurs
	 */
	@Throws(IOException::class)
	abstract fun write(connection: SmtpConnection)

}
