package net.tassia.pancake.scheduler.task

import kotlinx.coroutines.Job
import net.tassia.pancake.plugin.Plugin

abstract class JobTask(name: String, owner: Plugin, val job: Job) : AbstractTask(name, owner)
