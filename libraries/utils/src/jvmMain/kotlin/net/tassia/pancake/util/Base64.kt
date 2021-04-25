package net.tassia.pancake.util

actual object Base64 {

	actual fun encode(data: ByteArray): String {
		return java.util.Base64.getEncoder().encodeToString(data)
	}

	actual fun decode(base64: String): ByteArray {
		return java.util.Base64.getDecoder().decode(base64)
	}

}
