package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.PancakeHTTP;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

class GET_RootLogs implements HttpRoute {
	private final AdminRoutes routes;

	public GET_RootLogs(AdminRoutes routes) {
		this.routes = routes;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		routes.addSideNav(view, AdminRoutes.SIDENAV_ROOT);
		routes.addRootMailNav(view, AdminRoutes.ROOT_LOGS);

		try {
			String logs = new String(Files.readAllBytes(routes.rootLogsFile.toPath()));
			logs = "<pre>" + PancakeHTTP.escapeXSS(logs) + "</pre>";
			view.setContent(logs);
			return view.view("Root Logs");

		} catch (IOException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

}
