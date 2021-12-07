package net.tassia.pancake.plugin.http.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.tassia.pancake.event.auth.AuthLoginResult

@Serializable
data class AuthLoginResponseData(
	@SerialName("result") val result: AuthLoginResult
)
