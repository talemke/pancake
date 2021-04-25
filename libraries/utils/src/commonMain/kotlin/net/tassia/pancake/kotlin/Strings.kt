package net.tassia.pancake.kotlin

import net.tassia.pancake.util.Base16
import net.tassia.pancake.util.Base64

fun String.base16(): ByteArray = Base16.decode(this)
fun String.base64(): ByteArray = Base64.decode(this)
