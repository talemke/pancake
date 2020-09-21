package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRouteInternal {
	protected String path = null;
	protected Pattern pattern = null;
	protected HttpRoute route = null;

	protected boolean methodGET = false;
	protected boolean methodPOST = false;
	protected boolean methodPUT = false;
	protected boolean methodDELETE = false;
	protected boolean methodPATCH = false;

	protected boolean run(Pancake pancake, HttpRequest request) {
		// Check method
		if (request.isMethod("GET") && !methodGET) return false;
		if (request.isMethod("POST") && !methodPOST) return false;
		if (request.isMethod("PUT") && !methodPUT) return false;
		if (request.isMethod("DELETE") && !methodDELETE) return false;
		if (request.isMethod("PATCH") && !methodPATCH) return false;

		// Check path
		String uri = request.getRequestPath();
		if (uri.endsWith("/")) uri = uri.substring(0, uri.length() - 1);

		if (!uri.matches(path)) return false;
		Matcher matcher = pattern.matcher(uri);

		Stack<String> matches = new Stack<>();
		while (matcher.find()) {
			if (matcher.groupCount() == 0) break;
			for (int i = 0; i < matcher.groupCount(); i++) {
				matches.push(matcher.group(i + 1));
			}
		}

		// Execute route
		byte[] data = null;
		try {
			data = route.route(pancake, request, matches.toArray(new String[0]));
		} catch (Throwable ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
		}
		if (data != null) request.setResponse(data);
		return true;
	}
}
