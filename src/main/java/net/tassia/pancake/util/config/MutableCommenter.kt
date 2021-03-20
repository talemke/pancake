package net.tassia.pancake.util.config

/**
 * A mutable [ConfigCommenter], allows for easily managing commenting.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class MutableCommenter : ConfigCommenter {

	private val register = mutableMapOf<String, MutableMap<String, String>>()

	override fun comment(section: String?, name: String?): String? = get(section, name)

	operator fun set(section: String?, key: String?, value: String?) {
		val store = register[section ?: ""] ?: mutableMapOf()
		if (value != null) {
			store[key ?: ""] = value
		} else {
			store.remove(key ?: "")
		}
		register[section ?: ""] = store
	}

	operator fun get(section: String?, key: String?): String? {
		val comment = register[section ?: ""]?.get(key ?: "")
		return if (comment == "") null else comment
	}

}
