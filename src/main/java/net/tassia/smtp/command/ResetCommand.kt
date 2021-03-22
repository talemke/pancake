package net.tassia.smtp.command

import net.tassia.smtp.SMTP
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * The reset command resets the SMTP connection to the initial state. It erases all the buffers and state tables
 * (both sender and recipient). Reset gets only the positive server response – 250. At the same time, the SMTP
 * connection remains open and is ready for a new mail transaction.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#RSET">RSET Command</a>
 */
class ResetCommand : SmtpCommand() {

	override val responses = setOf(250)

	override fun write(connection: SmtpConnection) {
		connection.writer.write("$COMMAND${SMTP.CRLF}")
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "RSET"

	}

}
