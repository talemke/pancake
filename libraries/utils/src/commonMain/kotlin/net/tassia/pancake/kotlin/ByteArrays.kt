package net.tassia.pancake.kotlin

import net.tassia.pancake.util.Base16
import net.tassia.pancake.util.Base64

fun ByteArray.base16(): String = Base16.encode(this)
fun ByteArray.base64(): String = Base64.encode(this)
