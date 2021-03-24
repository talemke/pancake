package net.tassia.smtp.command

import net.tassia.pancake.InternetAddress
import net.tassia.smtp.SMTP
import net.tassia.smtp.SMTP.toSMTPString
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * The MAIL FROM command initiates a mail transfer. As an argument, MAIL FROM includes a sender mailbox (reverse-path).
 * For some types of reporting messages like non-delivery notifications, the reverse-path may be void.
 * Optional parameters may also be specified.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#MAIL_FROM">MAIL_FROM Command</a>
 */
class MailFromCommand(private val sender: InternetAddress) : SmtpCommand() {

	override val responses = setOf(250, 451, 452, 455, 503, 550, 552, 553, 555)

	override fun write(connection: SmtpConnection) {
		connection.writer.write("$COMMAND:\"${sender.toSMTPString()}\"${SMTP.CRLF}")
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "MAIL FROM"

	}

}
