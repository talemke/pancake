package net.tassia.pancake.util

/**
 * Represents a simple version.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class Version(

	/**
	 * The major version. If an update increases this version, it most-likely contains
	 * major changes greatly breaking backwards compatibility.
	 */
	val major: Int,

	/**
	 * The minor version. If an update increases this version, it is most-likely safe to update.
	 * Such updates usually just add new features and bug fixes. This number is reset when a new
	 * major version is released.
	 */
	val minor: Int,

	/**
	 * The patch version. If an update increases this version, usually no new features are added, but rather
	 * existing bugs have been patched. One should update as soon as possible to that version. This number
	 * is reset when a new minor or major version is released.
	 */
	val patch: Int,

	/**
	 * The build version or counter. A linearly increasing number, allowing for easy comparison between version.
	 * This counter is never reset.
	 */
	val build: Int?,

	/**
	 * The extension part of the version. Can, for example, mark this version as a pre-release.
	 */
	val extension: String?,

	/**
	 * The full Git-head of the version.
	 */
	val headFull: String?,

	/**
	 * The Git branch of this version.
	 */
	val branch: String?,

	/**
	 * The short Git-head of this version.
	 */
	val head: String? = headFull?.substring(0, 8),

) {

	/**
	 * Returns a nicely formatted string representing this version.
	 *
	 * @return string
	 */
	fun toDisplayString(): String {
		return toIdentifier()
	}

	/**
	 * Generates an identifier string for this version.
	 *
	 * @return the identifier
	 *
	 * @see [Version.parse]
	 */
	fun toIdentifier(): String {
		var str = "$major.$minor.$patch"
		if (build != null) str += ".$build"
		if (extension != null) str += "-$extension"
		if (headFull != null && branch != null) str += "-$branch@$headFull"
		else if (headFull != null) str += "-@$headFull"
		else if (branch != null) str += "-$branch@HEAD"
		return str
	}



	companion object {

		/**
		 * The pattern for version identifiers.
		 */
		private val REGEX = Regex("^([0-9]+).([0-9]+).([0-9]+)-([A-z0-9_-]+)$")

		/**
		 * Parses a version identifier string.
		 *
		 * @param input the identifier
		 * @return the version, or `null`
		 *
		 * @see [Version.toIdentifier]
		 */
		fun parse(input: String): Version? {
			println(input)
			val result = REGEX.matchEntire(input) ?: return null
			result.groupValues.forEach(::println)
			TODO()
		}

	}

}
