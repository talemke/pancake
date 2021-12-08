package net.tassia.pancake.scheduler.task

import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.scheduler.Task

abstract class AbstractTask(override val name: String, override val owner: Plugin) : Task()
