package net.tassia.pancake.scheduler.task

import kotlinx.coroutines.Job
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.scheduler.AsyncFunction

abstract class ImmediateTask(name: String, owner: Plugin, job: Job, val task: AsyncFunction)
	: JobTask(name, owner, job)
