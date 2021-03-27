package net.tassia.pancake.security.spam

/**
 * Represents the result of a spam check.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class SpamCheckResult {

	/**
	 * The mail is probably not spam.
	 *
	 * **Behavior:** The mail's routing should not be changed.
	 */
	NOT_SPAM,

	/**
	 * The mail is likely spam.
	 *
	 * **Behavior:** The mail's routing should not be changed.
	 * A small notice stating this mail possibly could be spam
	 * should be displayed when viewing the mail.
	 */
	LIKELY_SPAM,

	/**
	 * The mail is highly likely spam.
	 *
	 * **Behavior:** Move the mail to the spam folder and mark it as unread.
	 */
	HIGHLY_LIKELY_SPAM,

	/**
	 * The mail is almost certainly spam.
	 *
	 * **Behavior:** Move the mail to the spam folder and mark it as read.
	 */
	ALMOST_CERTAIN_SPAM;

}
