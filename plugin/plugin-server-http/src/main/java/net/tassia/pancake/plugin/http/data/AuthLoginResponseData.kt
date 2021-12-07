package net.tassia.pancake.plugin.http.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginResponseData(
	@SerialName("result") val result: Result,
	@SerialName("redirect_to") val redirectTo: String? = null
) {

	enum class Result {
		INVALID_CREDENTIALS,
		ACCOUNT_SUSPENDED,
		REDIRECT,
		SUCCESS;
	}

}
