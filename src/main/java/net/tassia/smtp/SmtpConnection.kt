package net.tassia.smtp

import java.io.EOFException
import java.io.IOException
import java.io.Reader
import java.io.Writer

/**
 * An SMTP connection consists of a writer, a reader and a charset.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 */
interface SmtpConnection {

	/**
	 * The writer.
	 */
	val writer: Writer

	/**
	 * The reader.
	 */
	val reader: Reader



	/**
	 * Closes the connection.
	 *
	 * @param safe whether to disconnect safely, i.e. send a QUIT command and await response
	 */
	fun disconnect(safe: Boolean)



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
	fun writeCommand(command: SmtpCommand): SmtpResponse {
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
	fun readResponse(): SmtpResponse {
		// Read first line
		val response = readResponseLine(null).let {
			if (it.second) return it.first else return@let it.first
		}

		// Read other lines
		while (true) {
			val res = readResponseLine(response)
			if (res.second) break
		}
		return response
	}

	@Throws(EOFException::class, IOException::class)
	private fun readResponseLine(response: SmtpResponse?): Pair<SmtpResponse, Boolean> {
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
		if (response.information.isEmpty()) {
			writeResponseLine(response.status, null, true)
		} else {
			response.information.forEachIndexed { index, value ->
				writeResponseLine(response.status, value, index == response.information.size - 1)
			}
		}
	}

	@Throws(IOException::class)
	private fun writeResponseLine(status: Int, information: String?, last: Boolean) {
		if (last) {
			if (information == null) writer.write(status.toString() + SMTP.CRLF)
			else writer.write("$status $information${SMTP.CRLF}")
		} else {
			writer.write("$status-$information${SMTP.CRLF}")
		}
	}

}
