package net.tassia.pancake.event.auth

import net.tassia.pancake.entity.Account

class AuthLoginEvent(val username: String, val password: String) {

	var result = AuthLoginResult.INVALID_USERNAME_OR_PASSWORD
	var account: Account? = null

}
