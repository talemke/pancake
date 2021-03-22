package net.tassia.smtp

import java.io.EOFException
import java.io.IOException
import java.io.Reader
import java.io.Writer
import java.nio.charset.Charset

/**
 * An SMTP connection consists of a writer, a reader and a charset.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 */
interface SmtpConnection {

	/**
	 * The charset.
	 */
	val charset: Charset

	/**
	 * The writer.
	 */
	val writer: Writer

	/**
	 * The reader.
	 */
	val reader: Reader



	/**
	 * Reads a command from the reader.
	 *
	 * @return the command
	 *
	 * @throws IOException if an I/O error occurs
	 * @throws EOFException if the stream reaches the end
	 */
	@Throws(EOFException::class, IOException::class)
	fun readCommand(): SmtpCommand? {
		TODO()
	}

	/**
	 * Writes a command to the writer.
	 *
	 * @param command the command
	 *
	 * @throws IOException if an I/O error occurs
	 */
	@Throws(IOException::class)
	open fun writeCommand(command: SmtpCommand): List<SmtpResponse> {
		command.write(this)
		return readResponse()
	}



	/**
	 * Reads a response from the reader.
	 *
	 * @return the response(s)
	 *
	 * @throws IOException if an I/O error occurs
	 * @throws EOFException if the stream reaches the end
	 */
	@Throws(EOFException::class, IOException::class)
	fun readResponse(): List<SmtpResponse> {
		TODO()
	}

	/**
	 * Writes a response to the writer.
	 *
	 * @param response the response
	 *
	 * @throws IOException if an I/O error occurs
	 */
	@Throws(IOException::class)
	fun writeResponse(response: SmtpResponse) {
		TODO()
	}

}
