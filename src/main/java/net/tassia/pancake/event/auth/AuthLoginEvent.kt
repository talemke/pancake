package net.tassia.pancake.event.auth

import net.tassia.pancake.entity.Account

/**
 * Called if a user attempts to login.
 *
 * @param username the username
 * @param password the password
 *
 * @since Pancake 1.0
 */
class AuthLoginEvent(val username: String, val password: String) : AuthEvent() {

	/**
	 * The result of the attempted login.
	 */
	var result: Result = Result.InvalidCredentials



	/**
	 * Represents the result of an attempted login.
	 */
	sealed class Result {

		/**
		 * The login credentials are invalid.
		 */
		object InvalidCredentials : Result()

		/**
		 * The login would have been successful, but the account is currently suspended.
		 */
		class AccountSuspended(val account: Account) : Result()

		/**
		 * The client should be directed to another website.
		 * Used to implement the OAuth login-flow.
		 */
		class Redirect(val link: String) : Result()

		/**
		 * The login was successful for the given account.
		 */
		class Success(val account: Account) : Result()

	}

}
