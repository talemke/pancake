package net.tassia.pancake.security.spam

import net.tassia.pancake.entity.mail.Mail
import net.tassia.pancake.util.version.Version

/**
 * A spam filter is used to differentiate legit mails and spam.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
abstract class SpamFilter {

	/**
	 * The name of this filter.
	 */
	abstract val name: String

	/**
	 * The version of this filter.
	 */
	abstract val version: Version



	/**
	 * Checks the given mail for spam.
	 *
	 * @param mail the mail to check
	 * @return the check result
	 */
	abstract fun check(mail: Mail): SpamCheckResult

}
