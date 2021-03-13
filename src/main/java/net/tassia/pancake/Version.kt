package net.tassia.pancake

import java.util.regex.Pattern

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
	val build: Int,

	/**
	 * The full Git-head of the version.
	 */
	val headFull: String,

	/**
	 * The short Git-head of this version.
	 */
	val head: String = headFull.substring(0, 8),

	/**
	 * The Git branch of this version.
	 */
	val branch: String,

	/**
	 * The type of this version.
	 */
	val type: VersionType

) {

	/**
	 * Formats this version to a displayable string. If this is a full release, the included information
	 * will be less in the display string.
	 *
	 * @return display string
	 */
	fun toDisplayString(): String {
		return when (type) {
			VersionType.SNAPSHOT -> "$major.$minor.$patch-SNAPSHOT-$branch@$head"
			VersionType.RELEASE -> "$major.$minor.$patch"
		}
	}

	/**
	 * Generates an identifier string for this version.
	 *
	 * @return the identifier
	 *
	 * @see [Version.parse]
	 */
	fun toIdentifier(): String = "$major.$minor.$patch-$type-$build-$branch@$headFull"



	companion object {

		/**
		 * The pattern for version identifiers.
		 */
		private val PATTERN = Pattern.compile("^([0-9]+).([0-9]+).([0-9]+)-([A-Z]+)-([0-9]+)-([A-z0-9-_]+)@([0-9a-f]+)$")!!

		/**
		 * Parses a version identifier string.
		 *
		 * @param input the identifier
		 * @return the version, or `null`
		 *
		 * @see [Version.toIdentifier]
		 */
		fun parse(input: String): Version? {
			val matcher = PATTERN.matcher(input)
			if (!matcher.matches()) return null

			return Version(
				major = matcher.group(1).toInt(),
				minor = matcher.group(2).toInt(),
				patch = matcher.group(3).toInt(),
				build = matcher.group(5).toInt(),
				headFull = matcher.group(7),
				branch = matcher.group(6),
				type = VersionType.valueOf(matcher.group(4))
			)
		}

	}

}
