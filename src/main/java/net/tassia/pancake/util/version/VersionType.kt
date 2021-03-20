package net.tassia.pancake.util.version

/**
 * Defines what type a [Version] is.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class VersionType {

	/**
	 * A snapshot. Most-likely not stable. Expect an update very soon.
	 */
	SNAPSHOT,

	/**
	 * A full release. As stable as possible.
	 */
	RELEASE;

}
