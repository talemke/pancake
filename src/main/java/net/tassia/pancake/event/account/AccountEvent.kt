package net.tassia.pancake.event.account

import net.tassia.pancake.entity.Account
import net.tassia.pancake.event.Event

abstract class AccountEvent(val account: Account) : Event()
