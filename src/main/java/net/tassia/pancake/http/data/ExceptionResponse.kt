package net.tassia.pancake.http.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The error response, usually accompanied by `500` status code.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class ExceptionResponse(exception: Throwable) {

	/**
	 * The name of the error.
	 */
	@JsonProperty("error")
	val error: String = exception.javaClass.name

	/**
	 * The message of the error.
	 */
	@JsonProperty("message")
	val message: String? = exception.message

	/**
	 * The cause of the error.
	 */
	@JsonProperty("cause")
	val cause: ExceptionResponse? = if (exception.cause != null) ExceptionResponse(exception.cause!!) else null

	/**
	 * The full stack trace of what caused the error.
	 */
	@JsonProperty("stack_trace")
	val stackTrace: Array<StackTraceResponse> = exception.stackTrace.let {
		return@let Array(it.size) { index -> StackTraceResponse(it[index]).also { res -> println(res) } }
	}

}
