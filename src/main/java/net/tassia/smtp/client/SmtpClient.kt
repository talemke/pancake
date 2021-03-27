package net.tassia.smtp.client

import net.tassia.pancake.InternetAddress
import net.tassia.pancake.MimeMessage
import net.tassia.smtp.SmtpCommand
import net.tassia.smtp.SmtpConnection
import net.tassia.smtp.SmtpResponse
import net.tassia.smtp.command.*
import java.io.*
import java.net.Socket
import java.nio.charset.Charset

/**
 * Represents an SMTP connection from the client-side.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 */
class SmtpClient(

	private val socket: Socket,
	charset: Charset = Charsets.UTF_8

) : SmtpConnection, Closeable {

	override val reader = InputStreamReader(socket.getInputStream(), charset)
	override val writer = OutputStreamWriter(socket.getOutputStream(), charset)

	override fun disconnect(safe: Boolean) {
		if (safe) quit() else close()
	}



	@Throws(IOException::class)
	fun send(message: MimeMessage, sender: InternetAddress, recipients: Set<InternetAddress>) {
		require(recipients.isNotEmpty()) { "At least 1 recipient is required." }

		writeCommand(MailFromCommand(sender))
		recipients.forEach { writeCommand(RecipientToCommand(it)) }
		writeCommand(DataCommand(message))
	}



	/**
	 * Sends a `RESET` command to the server.
	 */
	@Throws(IOException::class)
	fun reset() {
		writeCommand(ResetCommand())
	}

	/**
	 * Sends a `QUIT` command to the server and closes the connection.
	 */
	@Throws(IOException::class)
	fun quit() {
		writeCommand(QuitCommand())
		close()
	}



	override fun writeCommand(command: SmtpCommand): SmtpResponse {
		val response = super.writeCommand(command)
		if (!command.responses.contains(response.status)) {
			disconnect(false)
			throw IOException("Server responded with invalid response: ${response.status} - ${response.information}")
		}
		return response
	}



	/**
	 * Closes the connection to the SMTP server.
	 */
	override fun close() {
		socket.close()
	}

	/**
	 * Whether the connection to the SMTP server has been closed.
	 *
	 * @return is closed
	 */
	val isClosed: Boolean
		get() = socket.isClosed

}
