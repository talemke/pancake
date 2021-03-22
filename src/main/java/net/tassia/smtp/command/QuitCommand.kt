package net.tassia.smtp.command

import net.tassia.smtp.SMTP
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * The QUIT command send the request to terminate the SMTP session. Once the server responses with 221,
 * the client closes the SMTP connection. This command specifies that the receiver MUST send a `221 OK`
 * reply and then closes the transmission channel.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#QUIT">QUIT Command</a>
 */
class QuitCommand : SmtpCommand() {

	override val responses = setOf(221)

	override fun write(connection: SmtpConnection) {
		connection.writer.write("$COMMAND${SMTP.CRLF}")
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "QUIT"

	}

}
