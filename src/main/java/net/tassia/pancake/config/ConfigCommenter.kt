package net.tassia.pancake.config

/**
 * This class is used to determine how a config file should be commented.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun interface ConfigCommenter {

	/**
	 * Determines how to comment a section or a value.
	 *
	 * **Note:**
	 * The section will be `null` if it is the root section.
	 *
	 * **Note:**
	 * The name will be `null` if not a specific property should be comment, but the entire section.
	 *
	 * @param section the section
	 * @param name the name
	 * @return the comment, or `null` to not comment
	 */
	fun comment(section: String?, name: String?): String?

}
