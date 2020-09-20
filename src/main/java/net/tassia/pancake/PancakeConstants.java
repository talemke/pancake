package net.tassia.pancake;

public interface PancakeConstants {

	String UUID_REGEX = "([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})";
	String USERNAME_REGEX = "([A-Za-z0-9]+)";

	int TYPE_DEFAULT = 0;
	int TYPE_DRAFT = 1;
	int TYPE_SENT = 2;
	int TYPE_DELETED = 3;
	int TYPE_SPAM = 4;

	long FLAG_ACCOUNT_SUSPENDED = 1L;
	long FLAG_GROUP_ADMIN = 1L;

	int VERSION_MAJOR = 0;
	int VERSION_MINOR = 0;
	int VERSION_PATCH = 1;
	int VERSION_BUILD = 100;
	String VERSION_BRANCH = "master";
	String VERSION_HEAD_FULL = "16e4f3c758a208864c770c224697993833d2e483";
	String VERSION_HEAD = VERSION_HEAD_FULL.substring(0, 8);

	String ANSI_INFO = "\u001B[38;2;255;255;255m";
	String ANSI_WARNING = "\u001B[38;2;255;191;0m";
	String ANSI_SEVERE = "\u001B[101;93m";
	String ANSI_FINE = "\u001B[38;2;127;127;127m";

}
