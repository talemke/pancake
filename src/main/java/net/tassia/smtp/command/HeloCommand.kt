package net.tassia.smtp.command

import net.tassia.smtp.SMTP
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * The `HELO` command initiates the SMTP session conversation. The client greets the server and introduces itself.
 * As a rule, `HELO` is attributed with an argument that specifies the domain name or IP address of the SMTP client.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#HELOEHLO">HELO Command</a>
 */
class HeloCommand(private val hostname: String) : SmtpCommand() {

	override val responses = setOf(250, 502, 504, 550)

	override fun write(connection: SmtpConnection) {
		connection.writer.write("$COMMAND $hostname${SMTP.CRLF}")
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "HELO"

	}

}
