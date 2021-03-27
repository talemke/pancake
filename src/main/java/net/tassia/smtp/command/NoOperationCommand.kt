package net.tassia.smtp.command

import net.tassia.smtp.SMTP
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * The `NOOP` command is used only to check whether the server can respond. “250 OK” reply in response.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#NOOP">NOOP Command</a>
 */
class NoOperationCommand : SmtpCommand() {

	override val responses = setOf(250)

	override fun write(connection: SmtpConnection) {
		connection.writer.write("$COMMAND${SMTP.CRLF}")
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "NOOP"

	}

}
