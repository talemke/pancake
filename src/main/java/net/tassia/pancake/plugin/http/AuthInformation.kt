package net.tassia.pancake.plugin.http

import net.tassia.pancake.entity.account.Account
import net.tassia.pancake.entity.group.Group
import net.tassia.pancake.entity.group.GroupPrivilege
import net.tassia.pancake.entity.session.Session

/**
 * This data class stores authentication information about an HTTP request.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class AuthInformation(

	/**
	 * The session used to authenticate.
	 */
	val session: Session,

	/**
	 * The account who authenticated.
	 */
	val account: Account,

	/**
	 * The group of the account.
	 */
	val group: Group,

	/**
	 * The privilege required by the request.
	 */
	val privilege: GroupPrivilege?,

)
