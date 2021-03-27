package net.tassia.smtp.command

import net.tassia.pancake.InternetAddress
import net.tassia.smtp.SMTP
import net.tassia.smtp.SMTP.toSMTPString
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * The `RCPT TO` command specifies the recipient. As an argument, `RCPT TO` includes a destination mailbox
 * (forward-path). In case of multiple recipients, `RCPT TO` will be used to specify each recipient separately.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#RCPT_TO">RCPT TO Command</a>
 */
class RecipientToCommand(private val sender: InternetAddress) : SmtpCommand() {

	override val responses = setOf(250, 451, 452, 455, 503, 550, 552, 553, 555)

	override fun write(connection: SmtpConnection) {
		connection.writer.write("$COMMAND:\"${sender.toSMTPString()}\"${SMTP.CRLF}")
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "RCPT TO"

	}

}
