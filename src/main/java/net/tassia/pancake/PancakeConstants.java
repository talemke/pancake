package net.tassia.pancake;

public interface PancakeConstants {

	String UUID_REGEX = "([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})";

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

}
