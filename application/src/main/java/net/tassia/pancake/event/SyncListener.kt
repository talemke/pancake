package net.tassia.pancake.event

import net.tassia.pancake.plugin.Plugin

class SyncListener<E : Event>(val plugin: Plugin, val listener: (E) -> Unit)
