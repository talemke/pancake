package net.tassia.pancake.plugin.http.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginRequestData(
	@SerialName("username") val username: String,
	@SerialName("password") val password: String
)
