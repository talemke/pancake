package net.tassia.pancake.event.generic

import net.tassia.pancake.entity.Account

/**
 * Declares that a given event involves an [Account].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
interface AccountEvent {

	/**
	 * The account.
	 */
	val account: Account

}
