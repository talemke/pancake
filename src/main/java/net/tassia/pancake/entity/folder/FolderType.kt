package net.tassia.pancake.entity.folder

/**
 * Defines the type of a [Folder]. This also specifies if a folder may be deleted by the user.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class FolderType {

	/**
	 * The incoming mail folder. New mails are, by default, directed to this folder.
	 */
	NEW,

	/**
	 * The drafts folder. All mails not sent yet are stored in this folder.
	 */
	DRAFTS,

	/**
	 * The sent mail folder. All mails sent are stored in this folder.
	 */
	SENT,

	/**
	 * The trash folder. Deleted mails are moved to this folder instead of being fully deleted.
	 */
	TRASH,

	/**
	 * The spam folder. Mails which have been detected as being spam are directed to this folder.
	 */
	SPAM,

	/**
	 * A custom folder, created by the user.
	 */
	CUSTOM;

}
