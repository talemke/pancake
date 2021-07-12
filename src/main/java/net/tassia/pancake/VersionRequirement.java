package net.tassia.pancake;

/**
 * A version requirement is used usually in dependencies,
 * to declare a minimum required version.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public abstract class VersionRequirement {

	/**
	 * Whether the given version satisfies this requirement.
	 *
	 * @param version the version to test
	 * @return if the requirement is fulfilled
	 */
	public abstract boolean satisfies(Version version);

}
