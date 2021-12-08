package net.tassia.pancake.scheduler

import net.tassia.pancake.plugin.Plugin

abstract class Task {

	abstract val name: String
	abstract val owner: Plugin

}
