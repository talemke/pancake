package net.tassia.pancake.event.mail

import net.tassia.pancake.entity.Mail
import net.tassia.pancake.event.Event

abstract class MailEvent(val mail: Mail) : Event()
