package net.tassia.pancake.event.generic

import net.tassia.pancake.entity.Folder

/**
 * Declares that a given event involves a [Folder].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
interface FolderEvent {

	/**
	 * The folder.
	 */
	val folder: Folder

}
