package net.tassia.pancake.http;

public class HttpStaticRoute {
	public final byte[] data;
	public final String contentType;
	public final String charset;

	public HttpStaticRoute(byte[] data) {
		this(data, "text/plain");
	}

	public HttpStaticRoute(byte[] data, String contentType) {
		this(data, contentType, "utf-8");
	}

	public HttpStaticRoute(byte[] data, String contentType, String charset) {
		this.data = data;
		this.contentType = contentType;
		this.charset = charset;
	}

}
