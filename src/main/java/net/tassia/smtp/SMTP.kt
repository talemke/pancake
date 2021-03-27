package net.tassia.smtp

import net.tassia.pancake.InternetAddress
import net.tassia.pancake.MimeMessage
import net.tassia.smtp.client.SmtpClient
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel

/**
 * The base class for the SMTP implementation.
 * Contains useful constants.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 */
object SMTP {

	/**
	 * A string consisting of the carriage return (CR) and line feed (LN) character.
	 *
	 * `<CR><LF>`
	 */
	const val CRLF = "\r\n"



	/**
	 * Converts this internet address to an SMTP-valid string.
	 *
	 * @return SMTP string
	 */
	fun InternetAddress.toSMTPString(): String {
		return if (this.fullName != null) "${this.fullName} <$this>" else this.toString()
	}



	fun deliverAsync(message: MimeMessage, sender: InternetAddress, recipients: Set<InternetAddress>, hostname: String,
				server: String, port: Int) {
		require(recipients.isNotEmpty()) { "At least 1 recipient is required." }

		val socket = SocketChannel.open()
		socket.connect(InetSocketAddress(server, port))
		socket.configureBlocking(false)

		val client = SmtpClient(socket.socket())
		client.hello(hostname)
		client.send(message, sender, recipients)
		client.quit()
	}

}
