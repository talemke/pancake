package net.tassia.pancake.server.http.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.tassia.Namespace

@Serializable
data class StatusResponse(
	@SerialName("code") val code: Int,
	@SerialName("name") val name: String,
	@SerialName("description") val description: String
) {

	@Namespace
	companion object {

		val OK = StatusResponse(200, "OK", "The action completed successfully.")

		val BadRequest = StatusResponse(400, "Bad Request", "The request payload was malformed.")
		val Unauthorized = StatusResponse(401, "Unauthorized", "The action requires authentication, but no authentication took place.")
		val Forbidden = StatusResponse(403, "Forbidden", "The client does not have access to the given action.")
		val NotFound = StatusResponse(404, "Not Found", "The requested resource does not exist or is private.")

		val InternalServerError = StatusResponse(500, "Internal Server Error", "The server encountered in internal from which it could not recover.")



		operator fun get(code: Int): StatusResponse? {
			return when (code) {

				200 -> OK

				400 -> BadRequest
				401 -> Unauthorized
				403 -> Forbidden
				404 -> NotFound

				500 -> InternalServerError

				else -> null
			}
		}

	}

}
