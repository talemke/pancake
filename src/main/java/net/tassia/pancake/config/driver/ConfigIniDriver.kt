package net.tassia.pancake.config.driver

import net.tassia.pancake.config.ConfigDriver
import java.io.Reader
import java.io.Writer
import java.util.*

private typealias Config = Map<String, String>
private typealias SectionCommenter = (String?, String?) -> String?

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



	override fun write(writer: Writer, map: Config) = writeIni(writer, map)

	private fun insertData(section: String?, key: String, value: String, store: MutableMap<String, MutableMap<String, String>>) {
		val store2 = store[section ?: ""] ?: mutableMapOf()
		store2[key] = value
		store[section ?: ""] = store2
	}

	private fun writeIni(writer: Writer, config: Config, sectionCommenter: SectionCommenter = {_, _ -> null}) {
		// Load sections
		val sections = mutableMapOf<String, MutableMap<String, String>>()
		for (entry in config.entries) {
			val i = entry.key.lastIndexOf('.')
			if (i == -1) {
				insertData(null, entry.key, entry.value, sections)
			} else {
				insertData(entry.key.substring(0, i), entry.key.substring(i + 1), entry.value, sections)
			}
		}

		// Write root section
		sections[""]?.let { writeIniSection(writer, it, null, sectionCommenter) }

		// Write other section
		for (entry in sections.entries) {
			if (entry.key == "") continue
			writer.writeLine("")
			writeIniSection(writer, entry.value, entry.key, sectionCommenter)
		}
	}

	private fun writeIniSection(writer: Writer, config: Config, sectionName: String?, sectionCommenter: SectionCommenter) {
		// Write section comment
		sectionCommenter(sectionName, null)?.let { writer.writeLine(it) }

		// Write section header
		sectionName?.let { writer.writeLine("[$it]") }

		// Write values
		for (entry in config.entries) {
			// Write value comment
			sectionCommenter(sectionName, entry.key)?.let { writer.writeLine(it) }

			// Write value
			writer.writeLine(entry.key + " = " + entry.value)
		}
	}

	private fun Writer.writeLine(line: String) {
		write(line)
		write(System.lineSeparator())
	}

}
