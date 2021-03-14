package net.tassia.pancake.config.driver

import net.tassia.pancake.config.ConfigCommenter
import net.tassia.pancake.config.ConfigDriver
import net.tassia.pancake.config.ConfigIO
import java.io.Reader
import java.io.Writer
import java.util.*

private typealias Config = Map<String, String>

/**
 * The `.ini` file format.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object ConfigIniDriver : ConfigDriver("ini") {

	override fun read(reader: Reader): Map<String, String> {
		// Create and read properties
		val props = Properties()
		props.load(reader)

		// Transform to map
		val map = mutableMapOf<String, String>()
		for (name in props.stringPropertyNames()) {
			map[name] = props[name]?.toString() ?: continue
		}

		// Return map
		return map
	}



	override fun write(writer: Writer, map: Config, commenter: ConfigCommenter) = writeIni(writer, map, commenter)

	private fun insertData(section: String?, key: String, value: String, store: MutableMap<String, MutableMap<String, String>>) {
		val store2 = store[section ?: ""] ?: mutableMapOf()
		store2[key] = value
		store[section ?: ""] = store2
	}

	private fun writeIni(writer: Writer, config: Config, commenter: ConfigCommenter) {
		// Load sections
		val sections = mutableMapOf<String, MutableMap<String, String>>()
		for (entry in config.entries) {
			ConfigIO.examineSection(entry.key).let {
				insertData(it.first, it.second, entry.value, sections)
			}
		}

		// Write root section
		sections[""]?.let { writeIniSection(writer, it, null, commenter) }

		// Write other section
		for (entry in sections.entries) {
			if (entry.key == "") continue
			repeat(5) { writer.writeLine("") }
			writeIniSection(writer, entry.value, entry.key, commenter)
		}
	}

	private fun writeIniSection(writer: Writer, config: Config, sectionName: String?, commenter: ConfigCommenter) {
		// Write section comment
		commenter.comment(sectionName, null)?.let { writer.writeLine("; $it") }

		// Write section header
		sectionName?.let { writer.writeLine("[$it]") }

		// Write values
		for (entry in config.entries) {
			writer.writeLine("")

			// Write value comment
			commenter.comment(sectionName, entry.key)?.let { writer.writeLine("; $it") }

			// Write value
			writer.writeLine(entry.key + " = " + entry.value)
		}
	}

	private fun Writer.writeLine(line: String) {
		write(line)
		write(System.lineSeparator())
	}

}
