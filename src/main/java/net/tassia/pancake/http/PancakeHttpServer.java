package net.tassia.pancake.http;

import com.sun.net.httpserver.HttpServer;
import net.tassia.pancake.Arrays;
import net.tassia.pancake.Pancake;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class PancakeHttpServer {
	private final Pancake pancake;
	private final HttpServer server;
	private final List<HttpRouteInternal> routes;
	private final Map<String, HttpStaticRoute> statics;

	/* Constructor */
	public PancakeHttpServer(Pancake pancake) throws IOException {
		this.pancake = pancake;
		this.server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(pancake.getConfig().httpPort), pancake.getConfig().httpBacklog);
		this.routes = new ArrayList<>();
		this.statics = new HashMap<>();

		server.createContext("/", (exchange) -> {
			// Serve static assets
			if (exchange.getRequestMethod().equals("GET")) {
				String path = exchange.getRequestURI().getPath();
				HttpStaticRoute staticRoute = statics.get(path);
				if (staticRoute != null) {
					exchange.getResponseHeaders().add("Content-Type", staticRoute.contentType);
					exchange.getResponseHeaders().add("Accept-Charset", staticRoute.charset);
					exchange.sendResponseHeaders(200, staticRoute.data.length);
					exchange.getResponseBody().write(staticRoute.data);
					exchange.close();
					return;
				}
			}

			// Serve dynamic request
			HttpRequest req = new HttpRequest(pancake, exchange);
			dispatchRequest(req);
			try {
				req.setResponseHeader("Connection", "keep-alive");
				req.setResponseHeader("Content-Type", req.getContentType() + "; charset=utf-8");
				req.setResponseHeader("Server", "Pancake");

				byte[] data = req.getResponse();
				exchange.sendResponseHeaders(req.getResponseCode(), data.length);
				exchange.getResponseBody().write(data);

				exchange.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
	}
	/* Constructor */





	/* Routes */
	protected void dispatchRequest(HttpRequest request) {
		pancake.getLogger().fine("Received HTTP request '" + request.getRequestPath() + "' from " + request.getRemoteAddress().toString());
		for (HttpRouteInternal route : routes) {
			if (route.run(pancake, request)) {
				return;
			}
		}
		request.setErrorPage(404);
	}

	public void GET(String path, HttpRoute route) {
		MATCHES(path, route, "GET");
	}

	public void POST(String path, HttpRoute route) {
		MATCHES(path, route, "POST");
	}

	public void PUT(String path, HttpRoute route) {
		MATCHES(path, route, "PUT");
	}

	public void DELETE(String path, HttpRoute route) {
		MATCHES(path, route, "DELETE");
	}

	public void PATCH(String path, HttpRoute route) {
		MATCHES(path, route, "PATCH");
	}

	public void ANY(String path, HttpRoute route) {
		MATCHES(path, route, "GET", "POST", "PUT", "DELETE", "PATCH");
	}

	public void MATCHES(String path, HttpRoute route, String...methods) {
		pancake.getLogger().finer("  - Adding route: " + path);
		HttpRouteInternal r = new HttpRouteInternal();

		r.path = path;
		r.pattern = Pattern.compile(path);
		r.route = route;

		r.methodGET = Arrays.contains(methods, "GET");
		r.methodPOST = Arrays.contains(methods, "POST");
		r.methodPUT = Arrays.contains(methods, "PUT");
		r.methodDELETE = Arrays.contains(methods, "DELETE");
		r.methodPATCH = Arrays.contains(methods, "PATCH");

		routes.add(r);
	}

	public void serveStaticData(String path, byte[] data, String contentType, String charset) {
		pancake.getLogger().finer("  - Adding static: " + path);
		statics.put(path, new HttpStaticRoute(data, contentType, charset));
	}

	public void serveStaticResource(String path, String resourcePath, String contentType, String charset) {
		try {
			byte[] data = Pancake.class.getResourceAsStream(resourcePath).readAllBytes();
			serveStaticData(path, data, contentType, charset);
		} catch (IOException ex) {
			throw new Error("Failed to load resource " + resourcePath, ex);
		}
	}
	/* Routes */





	/* Start */
	public void start() {
		server.start();
	}
	/* Start */

}
