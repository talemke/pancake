package net.tassia.smtp.command

import net.tassia.pancake.MimeMessage
import net.tassia.smtp.SMTP
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection

/**
 * With the `DATA` command, the client asks the server for permission to transfer the mail data.
 * The response code `354` grants permission, and the client launches the delivery of the email contents line by line.
 * This includes the date, from header, subject line, to header, attachments, and body text.
 *
 * A final line containing a period (“.”) terminates the mail data transfer. The server responses to the final line.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 *
 * @see <a href="https://blog.mailtrap.io/smtp-commands-and-responses/#DATA">DATA Command</a>
 */
class DataCommand(private val message: MimeMessage) : SmtpCommand() {

	init {
		// Verify headers
		for (header in message.headers) {
			require(!header.key.contains('\r')) {
				"Header ${header.key} may not contain <CR>."
			}
			require(!header.key.contains('\n')) {
				"Header ${header.key} may not contain <LF>."
			}

			require(!header.value.contains('\r')) {
				"Header ${header.key}: ${header.value} may not contain <CR>."
			}
			require(!header.value.contains('\n')) {
				"Header ${header.key}: ${header.value} may not contain <LF>."
			}
		}

		// Verify body
		require (!message.body.contains(SMTP.CRLF + "." + SMTP.CRLF)) {
			"Mime-Message body may not contain <CR><LF>.<CR><LF>"
		}
	}

	override val responses = setOf(250, 450, 451, 452, 503, 550, 552, 554)
	// Technically, 354 is also a valid response code. However we intercept
	// that response during processing to start sending our data, so this
	// response should never be received anywhere outside the 'write' function.

	override fun write(connection: SmtpConnection) {
		// Tell the server we want to transmit our data
		connection.writer.write("$COMMAND${SMTP.CRLF}")

		// Wait for valid response
		val response = connection.readResponse()
		if (response.status != 354) {
			connection.disconnect(true)
			return
		}

		// Send headers
		for (header in message.headers) {
			connection.writer.write(header.key + ": " + header.value + SMTP.CRLF)
		}
		connection.writer.write(SMTP.CRLF)

		// Send body
		connection.writer.write(message.body)

		// Send <CR><LF>.<CR><LF>
		connection.writer.write(SMTP.CRLF + "." + SMTP.CRLF)
	}



	companion object {

		@Suppress("SpellCheckingInspection")
		const val COMMAND = "DATA"

	}

}
