package net.tassia.pancake.event.option

import net.tassia.pancake.entity.PluginOption

abstract class PluginOptionEvent(option: PluginOption) : OptionEvent<PluginOption>(option)
