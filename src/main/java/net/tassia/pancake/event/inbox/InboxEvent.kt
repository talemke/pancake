package net.tassia.pancake.event.inbox

import net.tassia.pancake.entity.Inbox
import net.tassia.pancake.event.Event

abstract class InboxEvent(val inbox: Inbox) : Event()
