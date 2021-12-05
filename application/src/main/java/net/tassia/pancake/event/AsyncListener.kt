package net.tassia.pancake.event

import net.tassia.pancake.plugin.Plugin

class AsyncListener<E : Event>(val plugin: Plugin, val listener: suspend (E) -> Unit)
