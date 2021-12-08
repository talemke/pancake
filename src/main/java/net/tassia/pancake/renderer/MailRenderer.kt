package net.tassia.pancake.renderer

import net.tassia.pancake.entity.Mail

/**
 * A MailRenderer is used to render a [Mail] into valid and safe HTML.
 *
 * @since Pancake 1.0
 */
abstract class MailRenderer {

	/**
	 * Renders the given mail to HTML.
	 *
	 * **Note:** Pancake expects the rendered HTML to be safe for display.
	 *
	 * @param mail the mail to render
	 * @return the rendered HTML
	 */
	abstract fun render(mail: Mail): String

}
