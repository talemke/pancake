package net.tassia.pancake.scheduler

import kotlinx.coroutines.CoroutineScope

typealias AsyncFunction = suspend CoroutineScope.() -> Unit
