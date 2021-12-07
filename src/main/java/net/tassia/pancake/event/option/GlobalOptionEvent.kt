package net.tassia.pancake.event.option

import net.tassia.pancake.entity.GlobalOption

abstract class GlobalOptionEvent(option: GlobalOption) : OptionEvent<GlobalOption>(option)
