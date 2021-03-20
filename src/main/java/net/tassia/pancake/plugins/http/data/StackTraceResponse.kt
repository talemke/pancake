package net.tassia.pancake.plugins.http.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A single stack trace element. Sent in an [ExceptionResponse].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class StackTraceResponse(element: StackTraceElement) {

	/**
	 * The class name.
	 */
	@JsonProperty("class_name")
	val className: String = element.className

	/**
	 * The method name.
	 */
	@JsonProperty("method_name")
	val methodName: String = element.methodName

	/**
	 * The file name.
	 */
	@JsonProperty("file_name")
	val fileName: String? = element.fileName

	/**
	 * The line number.
	 */
	@JsonProperty("line_number")
	val lineNumber: Int = element.lineNumber

}
