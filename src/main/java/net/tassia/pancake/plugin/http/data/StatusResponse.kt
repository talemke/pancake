package net.tassia.pancake.plugins.http.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Defines a status response. This is sent back to the client to indicate that a request either has no response
 * (e.g. by sending an [OK] response) or that something went wrong (e.g. by sending an [Forbidden] response).
 *
 * @param code the status code (e.g. 200)
 * @param name the status name (e.g. 'OK')
 * @param information additional information
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class StatusResponse(

	/**
	 * The status code (e.g. 200).
	 */
	@JsonProperty("code")
	val code: Int,

	/**
	 * The status name (e.g. 'OK').
	 */
	@JsonProperty("name")
	val name: String,

	/**
	 * Additional information.
	 */
	@JsonProperty("information")
	val information: String,

) {

	/**
	 * The `200 - OK` response.
	 */
	class OK(information: String = "The requested action has been executed successfully."):
		StatusResponse(200, "OK", information)

	/**
	 * The `400 - Bad Request` response.
	 */
	class BadRequest(information: String = "The request was malformed."):
		StatusResponse(400, "Bad Request", information)

	/**
	 * The `401 - Unauthorized` response.
	 */
	class Unauthorized(information: String = "This request requires you to be authorized."):
		StatusResponse(401, "Unauthorized", information)

	/**
	 * The `403 - Forbidden` response.
	 */
	class Forbidden(information: String = "Your authentication does not allow you to perform this action."):
		StatusResponse(403, "Forbidden", information)

	/**
	 * The `404 - Not Found` response.
	 */
	class NotFound(information: String = "The requested resource does not exist or has been hidden."):
		StatusResponse(404, "Not Found", information)

}
