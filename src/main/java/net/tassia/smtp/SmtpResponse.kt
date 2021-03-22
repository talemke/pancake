package net.tassia.smtp

/**
 * Represents a response from the SMTP server.
 *
 * @since PancakeSMTP 1.0
 * @author Tassilo
 */
data class SmtpResponse(

	/**
	 * The status code.
	 */
	val status: Int,

	/**
	 * Additional information sent with the response.
	 */
	val information: String?,

)
