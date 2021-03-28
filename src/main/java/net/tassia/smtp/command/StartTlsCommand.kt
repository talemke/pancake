package net.tassia.smtp.command

import net.tassia.smtp.SMTP
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * The STARTTLS command is used to start a TLS handshake for a secure SMTP session. STARTTLS resets the SMTP protocol
 * to the initial state. Once the response `220` is received from the server, the SMTP client should send HELO or EHLO
 * to launch the session. In the case of a negative response (`454`), the client must decide whether to continue
 * the SMTP session or not.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#STARTTLS">STARTTLS Command</a>
 */
class StartTlsCommand : SmtpCommand() {

	override val responses = setOf(220, 454)

	override fun write(connection: SmtpConnection) {
		connection.writer.write("$COMMAND${SMTP.CRLF}")
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "STARTTLS"

	}

}
