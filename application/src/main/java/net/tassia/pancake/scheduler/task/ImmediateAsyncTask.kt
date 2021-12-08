package net.tassia.pancake.scheduler.task

import kotlinx.coroutines.Job
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.scheduler.AsyncFunction

class ImmediateAsyncTask(name: String, owner: Plugin, job: Job, task: AsyncFunction)
	: ImmediateTask(name, owner, job, task)
