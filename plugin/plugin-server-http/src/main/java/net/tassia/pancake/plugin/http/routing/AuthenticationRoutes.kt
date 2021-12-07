package net.tassia.pancake.plugin.http.routing

import io.ktor.http.*
import net.tassia.pancake.event.auth.AuthLoginEvent
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.http.data.AuthLoginRequestData
import net.tassia.pancake.plugin.http.data.AuthLoginResponseData
import net.tassia.pancake.server.http.routing.Router

internal fun Router.installAuthenticationRoutes(plugin: Plugin) {

	post(plugin, "/api/v1/auth/login") {

		// Read payload
		val data = receiveJSON<AuthLoginRequestData>()

		// Create & dispatch event
		val result = AuthLoginEvent(data.username, data.password).let {
			plugin.callEvent(it)
			return@let it.result
		}

		// Validate result
		when (result) {
			is AuthLoginEvent.Result.InvalidCredentials -> {
				val response = AuthLoginResponseData(AuthLoginResponseData.Result.INVALID_CREDENTIALS)
				respondJSON(response, HttpStatusCode.OK)
			}
			is AuthLoginEvent.Result.Success -> {
				// TODO
				val response = AuthLoginResponseData(AuthLoginResponseData.Result.SUCCESS)
				respondJSON(response, HttpStatusCode.OK)
			}
			is AuthLoginEvent.Result.Redirect -> {
				val response = AuthLoginResponseData(AuthLoginResponseData.Result.REDIRECT, redirectTo = result.link)
				respondJSON(response, HttpStatusCode.OK)
			}
			is AuthLoginEvent.Result.AccountSuspended -> {
				// TODO
				val response = AuthLoginResponseData(AuthLoginResponseData.Result.ACCOUNT_SUSPENDED)
				respondJSON(response, HttpStatusCode.OK)
			}
		}
	}

	post(plugin, "/api/v1/auth/logout") {
		TODO()
	}

	get(plugin, "/api/v1/auth/self") {
		TODO()
	}

}
