package net.tassia.pancake.server.http.routing

import net.tassia.pancake.server.http.transaction.HttpTransaction

typealias RouteExecutor = suspend HttpTransaction.() -> Unit
