package net.tassia.pancake.cli

data class CommandInfo(

	val name: String,
	val description: String,
	val aliases: Set<String> = setOf(),
	val usages: Map<String, String> = mapOf()

)
