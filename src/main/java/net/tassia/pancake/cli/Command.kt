package net.tassia.pancake.cli

import net.tassia.pancake.Pancake

fun interface Command {

	fun onCommand(pancake: Pancake, args: List<String>, toggles: Map<String, Boolean>, flags: Map<String, String>)

}
