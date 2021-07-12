package net.tassia.pancake;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * General I/O operations and utilities for Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class PancakeIO {

	public static final File rootDir = new File(".");

	public static final File archiveDir = new File(rootDir, "archive");

	public static final File backupsArchiveDir = new File(archiveDir, "backups");

	public static final File cacheDir = new File(rootDir, "cache");

	public static final File configDir = new File(rootDir, "config");

	public static final File dataDir = new File(rootDir, "data");

	public static final File libsDir = new File(rootDir, "libs");

	public static final File logsDir = new File(rootDir, "logs");
	public static final File binaryLogsDir = new File(logsDir, "binary");

	public static final File tempDir = new File(rootDir, "temp");





	/**
	 * Creates the given directory.
	 *
	 * This function will do nothing, if the directory already exists.
	 * Otherwise, it will try to create the directory and throw a
	 * {@link PancakeIOException} if the directory cannot be created.
	 *
	 * @param dir the directory to create
	 * @throws PancakeIOException if the directory cannot be created
	 */
	public static void createDirectory(File dir) throws PancakeIOException {
		// Directory already exists, ignore.
		if (dir.exists() && dir.isDirectory()) return;

		// Create the directory
		try {
			boolean success = dir.mkdirs();
			if (success) return;
		} catch (Throwable ex) {
			throw new PancakeIOException("Failed to create directory: " + dir, ex);
		}

		// Creation failed, throw error
		throw new PancakeIOException("Failed to create directory: " + dir);
	}

	/**
	 * Creates the given file.
	 *
	 * This function will do nothing, if the file already exists.
	 * Otherwise, it will try to create the file and throw a
	 * {@link PancakeIOException} if the file cannot be created.
	 *
	 * @param file the file to create
	 * @throws PancakeIOException if the file cannot be created
	 */
	public static void createFile(File file) throws PancakeIOException {
		// File already exists, ignore.
		if (file.exists() && file.isFile()) return;

		// Create the parent directory
		createDirectory(file.getParentFile());

		// Create the file
		try {
			boolean success = file.createNewFile();
			if (success) return;
		} catch (Throwable ex) {
			throw new PancakeIOException("Failed to create file: " + file, ex);
		}

		// Creation failed, throw error
		throw new PancakeIOException("Failed to create file: " + file);
	}

	/**
	 * Deletes the given file. This function will do nothing, if the file does not exist.
	 * Otherwise, it will try to delete the file and throw a {@link PancakeIOException}
	 * if the file cannot be deleted.
	 *
	 * @param file the file to delete
	 * @throws PancakeIOException if the file cannot be deleted
	 */
	public static void deleteFile(File file) throws PancakeIOException {
		// File does not exist, ignore.
		if (!file.exists() && !file.isFile()) return;

		// Delete the file
		try {
			boolean success = file.delete();
			if (success) return;
		} catch (Throwable ex) {
			throw new PancakeIOException("Failed to delete file: " + file, ex);
		}

		// Deletion failed, throw error
		throw new PancakeIOException("Failed to delete file: " + file);
	}

	public static void deleteDirectory(File dir) throws PancakeIOException {
		if (!dir.exists() && !dir.isDirectory()) return;

		// Delete children first
		File[] children = dir.listFiles();
		if (children != null) {
			for (File child : children) {
				if (child.exists() && child.isDirectory()) {
					deleteDirectory(child);
				} else if (child.exists() && child.isFile()) {
					deleteFile(child);
				} else if (child.exists()) {
					throw new PancakeIOException("Unsure how to delete file: " + child);
				}
			}
		}

		// Delete the directory
		try {
			boolean success = dir.delete();
			if (success) return;
		} catch (Throwable ex) {
			throw new PancakeIOException("Failed to delete directory: " + dir, ex);
		}

		// Deletion failed, throw error
		throw new PancakeIOException("Failed to delete directory: " + dir);
	}





	/**
	 * Returns the textual log file for the given date.
	 *
	 * @param timestamp the date
	 * @return the log file
	 */
	public static File getTextLogFile(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy'.'MM'.'dd'-'HH'.'mm");
		String date = format.format(new Date(timestamp));
		return new File(logsDir, date + ".txt");
	}

	/**
	 * Returns the binary log file for the given date.
	 *
	 * @param timestamp the date
	 * @return the log file
	 */
	public static File getBinaryLogFile(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy'.'MM'.'dd'-'HH'.'mm");
		String date = format.format(new Date(timestamp));
		return new File(binaryLogsDir, date + ".bin");
	}





	/**
	 * Flushes (deletes all nested files) the temporary directory.
	 */
	public static void flushTemporaryDirectory() {
		deleteDirectory(tempDir);
	}

	/**
	 * Ensures all directories required by the Pancake application are created.
	 */
	public static void createApplicationDirectories() {
		createDirectory(rootDir);
		createDirectory(archiveDir);
		createDirectory(backupsArchiveDir);
		createDirectory(cacheDir);
		createDirectory(configDir);
		createDirectory(dataDir);
		createDirectory(libsDir);
		createDirectory(logsDir);
		createDirectory(binaryLogsDir);
		createDirectory(tempDir);
	}





	/**
	 * Static class.
	 */
	private PancakeIO() {
	}

}
