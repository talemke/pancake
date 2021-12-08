package net.tassia.pancake.event.option

import net.tassia.pancake.entity.Option
import net.tassia.pancake.event.Event

abstract class OptionEvent<T : Option<T>>(val option: T) : Event()
