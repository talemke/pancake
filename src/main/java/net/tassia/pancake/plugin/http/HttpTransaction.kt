package net.tassia.pancake.plugins.http

import io.ktor.application.*
import io.ktor.http.*
import net.tassia.pancake.plugins.http.data.StatusResponse

/**
 * Represents a simple HTTP transaction.
 *
 * @param call the internal [ApplicationCall]
 * @param request the request payload (`null` for `GET` requests)
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class HttpTransaction<T>(call: ApplicationCall, var request: T?) {

	/**
	 * Parameters of this transaction.
	 */
	val parameters: Parameters = call.parameters

	/**
	 * The IP-address of the client. This will **not** always be the actual IP-address of the connecting client.
	 * If the installation is configured to e.g. use CloudFlare as a Proxy server, it will determine the real
	 * clients IP-address from the sent header. However, even then, this does not guarantee to hold the actual
	 * clients IP-address, as they might still be using e.g. a VPN.
	 */
	val address = call.request.local.remoteHost

	/**
	 * The [HttpStatusCode] to send in the response.
	 */
	var status: HttpStatusCode = HttpStatusCode.OK

	/**
	 * The actual response to send back.
	 */
	var response: Any = Object()

	/**
	 * Authentication information.
	 */
	var auth: AuthInformation? = null



	/**
	 * Sends a `400 - Bad Request` response.
	 *
	 * @param information additional information
	 */
	fun respondBadRequest(information: String? = null): StatusResponse {
		this.status = HttpStatusCode.BadRequest
		return information?.let { StatusResponse.BadRequest(information) } ?: StatusResponse.BadRequest()
	}

	/**
	 * Sends a `401 - Unauthorized` response.
	 *
	 * @param information additional information
	 */
	fun respondUnauthorized(information: String? = null): StatusResponse {
		this.status = HttpStatusCode.Unauthorized
		return information?.let { StatusResponse.Unauthorized(information) } ?: StatusResponse.Unauthorized()
	}

	/**
	 * Sends a `403 - Forbidden` response.
	 *
	 * @param information additional information
	 */
	fun respondForbidden(information: String? = null): StatusResponse {
		this.status = HttpStatusCode.Forbidden
		return information?.let { StatusResponse.Forbidden(information) } ?: StatusResponse.Forbidden()
	}

	/**
	 * Sends a `404 - Not Found` response.
	 *
	 * @param information additional information
	 */
	fun respondNotFound(information: String? = null): StatusResponse {
		this.status = HttpStatusCode.NotFound
		return information?.let { StatusResponse.NotFound(information) } ?: StatusResponse.NotFound()
	}

}
