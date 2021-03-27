package net.tassia.pancake.plugin.webhook.dispatcher.json

import net.tassia.pancake.plugin.webhook.entity.event.MailMovedEvent
import net.tassia.pancake.plugin.webhook.entity.event.MailReceivedEvent
import net.tassia.pancake.plugin.webhook.entity.event.MailSentEvent


class JsonMailReceivedEvent(event: MailReceivedEvent)

class JsonMailMovedEvent(event: MailMovedEvent)

class JsonMailSentEvent(event: MailSentEvent)
