package net.tassia.pancake.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private final HttpExchange exchange;
	private byte[] response;
	private int code;
	private byte[] data;
	private String contentType;
	private final Map<String, String> cookies;
	private Account account;

	/* Constructor */
	protected HttpRequest(Pancake pancake, HttpExchange exchange) {
		this.exchange = exchange;
		this.response = null;
		this.code = 200;
		this.data = null;
		this.contentType = "text/html";
		this.cookies = new HashMap<>();
		this.account = null;

		// Parse cookies
		String cookiesStr = getRequestHeaders().getFirst("Cookie");
		if (cookiesStr != null) {
			String[] cookies = cookiesStr.split(";");
			for (String cookie : cookies) {
				cookie = cookie.trim();
				String key = cookie.split("=", 2)[0];
				String value = cookie.substring(key.length() + 1);
				this.cookies.put(key, value);
			}
		}

		// Check authentication
		String sessionId = getCookie("PancakeSessionID");
		if (sessionId != null) {
			if (pancake.getHTTP().isRootSession(sessionId)) {
				account = Account.ROOT;
			} else {
				account = pancake.getHTTP().getAccountBySessionID(sessionId);
			}
		}

		// Parse request body
		InputStream in = exchange.getRequestBody();
		if (in != null) {
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int cache;
				while ((cache = in.read()) != -1) out.write(cache);
				in.close();
				this.data = out.toByteArray();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	/* Constructor */





	/* Generic */
	public InetSocketAddress getRemoteAddress() {
		return exchange.getRemoteAddress();
	}
	/* Generic */





	/* Request Getters */
	public String getMethod() {
		return exchange.getRequestMethod();
	}

	public boolean isMethod(String method) {
		return getMethod().equals(method);
	}

	public URI getRequestURI() {
		return exchange.getRequestURI();
	}

	public String getRequestPath() {
		return getRequestURI().getPath();
	}

	public Headers getRequestHeaders() {
		return exchange.getRequestHeaders();
	}

	public byte[] getRequestBody() {
		return data;
	}
	/* Request Getters */





	/* Response */
	protected byte[] getResponse() {
		return response;
	}

	protected void setResponse(byte[] response) {
		this.response = response;
	}

	public int getResponseCode() {
		return code;
	}

	public void setResponseCode(int code) {
		this.code = code;
	}
	/* Response */





	/* Response Headers */
	public void setResponseHeader(String header, String value) {
		removeResponseHeader(header);
		addResponseHeader(header, value);
	}

	public void addResponseHeader(String header, String value) {
		exchange.getResponseHeaders().add(header, value);
	}

	public void removeResponseHeader(String header) {
		exchange.getResponseHeaders().remove(header);
	}
	/* Response Headers */





	/* Cookies */
	public String getCookie(String key) {
		return cookies.get(key);
	}

	public void setCookie(String key, String value) {
		cookies.put(key, value);
		addResponseHeader("Set-Cookie", key + "=" + value);
	}
	/* Cookies */





	/* Authentication */
	public boolean checkAuth() {
		return account != null;
	}

	public Account getAuth() {
		return account;
	}
	/* Authentication */






	/* Redirect */
	public void redirect(String to) {
		setResponseCode(302);
		setContentType("text/plain");
		addResponseHeader("Location", to);
		setResponse("Redirected.".getBytes(StandardCharsets.UTF_8));
	}
	/* Redirect */





	/* Show Error Page */
	public void setErrorPage(int errorPage) {
		setContentType("text/plain");
		setResponseCode(errorPage);
		switch (errorPage) {
			case 400:
				setResponse("Bad Request".getBytes(StandardCharsets.UTF_8));
				return;

			case 401:
				setResponse("Unauthorized".getBytes(StandardCharsets.UTF_8));
				return;

			case 403:
				setResponse("Forbidden".getBytes(StandardCharsets.UTF_8));
				return;

			case 404:
				setResponse("Not Found".getBytes(StandardCharsets.UTF_8));
				return;

			case 500:
				setResponse("Internal Server Error".getBytes(StandardCharsets.UTF_8));
				return;

			default:
				setResponseCode(500);
				throw new RuntimeException("Unknown error code: " + errorPage);
		}
	}
	/* Show Error Page */





	/* Content Type */
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/* Content Type */





	/* Unsafe */
	@Deprecated
	public HttpExchange unsafe() {
		return exchange;
	}
	/* Unsafe */

}
