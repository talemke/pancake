package net.tassia.pancake;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Represents a version.
 *
 * @implNote This class is immutable.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class Version implements Comparable<Version> {

	public final int major;
	public final int minor;
	public final int patch;
	public final int build;

	public final long timestamp;
	@Nullable public final String extension;
	public final boolean isSnapshot;

	@Nullable public final String gitFullHead;
	@Nullable public final String gitHead;
	@Nullable public final String gitBranch;



	public Version(int major, int minor, int patch, int build, long timestamp, @Nullable String extension,
				   boolean isSnapshot, @Nullable String gitFullHead, @Nullable String gitBranch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.build = build;

		this.timestamp = timestamp;
		this.extension = extension;
		this.isSnapshot = isSnapshot;

		this.gitFullHead = gitFullHead;
		this.gitHead = gitFullHead != null ? gitFullHead.substring(0, 7) : null;
		this.gitBranch = gitBranch;
	}



	@Override
	public int compareTo(@NotNull Version o) {
		if (major != o.major) return major - o.major;
		if (minor != o.minor) return minor - o.minor;
		if (patch != o.patch) return patch - o.patch;
		if (build != o.build) return build - o.build;
		return 0;
	}

	/**
	 * Generates a display string for this version.
	 *
	 * If this version has an extension linked to it, the returned string follows this format:
	 * <pre>MAJOR.MINOR.PATCH-EXTENSION</pre>
	 * For example:
	 * <pre>1.4.2-SNAPSHOT</pre>
	 *
	 * If this version has no extension linked to it, the returned string follows this format:
	 * <pre>MAJOR.MINOR.PATCH</pre>
	 * For example:
	 * <pre>1.4.2</pre>
	 *
	 * @return a display string for this version
	 */
	@Override
	public String toString() {
		return extension != null ? String.format("%d.%d.%d-%s", major, minor, patch, extension) : String.format("%d.%d.%d", major, minor, patch);
	}



	/**
	 * Reads the given resource as {@link Properties}, and then parses a {@link Version} out of it.
	 *
	 * @param path the resource path
	 * @return the read version
	 * @throws PancakeIOException if an I/O error occurred
	 */
	public static Version fromResource(String path) throws PancakeIOException {
		Properties props = new Properties();

		// Get resource as stream
		try (InputStream in = Pancake.class.getResourceAsStream(path)) {
			// Check if resource exists
			if (in == null) {
				throw new PancakeIOException("Resource '" + path + "' does not exist in classpath.");
			}

			// Create and populate properties
			try {
				props.load(in);
			} catch (IOException ex) {
				throw new PancakeIOException("Failed to parse resource as properties.", ex);
			}
		} catch (IOException ex) {
			throw new PancakeIOException("Failed to access resource: " + path, ex);
		}

		// Read from properties
		return fromProperties(props);
	}

	/**
	 * Reads a {@link Version} from the given {@link Properties}.
	 *
	 * @param props the properties to read from
	 * @return the generated version
	 */
	public static Version fromProperties(Properties props) {
		// General information
		int major = Integer.parseInt(props.getProperty("VERSION_MAJOR"));
		int minor = Integer.parseInt(props.getProperty("VERSION_MINOR"));
		int patch = Integer.parseInt(props.getProperty("VERSION_PATCH"));
		int build = Integer.parseInt(props.getProperty("VERSION_BUILD"));

		// Meta information
		long timestamp = Long.parseLong(props.getProperty("TIMESTAMP"));
		String extension = props.getProperty("EXTENSION");
		if (extension.isEmpty() || extension.equals("null")) extension = null;
		boolean isSnapshot = Boolean.parseBoolean(props.getProperty("SNAPSHOT"));

		// Git information
		String gitHead = props.getProperty("GIT_HEAD");
		if (gitHead == null) throw new IllegalArgumentException("GitHead is not provided.");
		String gitBranch = props.getProperty("GIT_BRANCH");
		if (gitBranch == null) throw new IllegalArgumentException("GitBranch is not provided.");

		// Construct version
		return new Version(major, minor, patch, build, timestamp, extension, isSnapshot, gitHead, gitBranch);
	}

}
