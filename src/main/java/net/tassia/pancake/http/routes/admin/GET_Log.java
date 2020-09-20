package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

class GET_Log implements HttpRoute {
	private final AdminRoutes routes;
	private final HttpView logView;

	public GET_Log(AdminRoutes routes) {
		this.routes = routes;
		this.logView = new HttpView("/views/log/log.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;
		if (view.checkAccess(request.getAuth().isRoot())) return null;

		File log = new File("logs/" + matches[0] + ".txt");
		if (!log.exists()) {
			request.setErrorPage(404);
			return null;
		}
		long time = Long.parseLong(matches[0], 16);

		byte[] logData;
		try {
			logData = Files.readAllBytes(log.toPath());
		} catch (IOException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}

		routes.addSideNav(request.getAuth(), view, AdminRoutes.SIDENAV_LOGS);
		routes.addLogsMailNav(view, matches[0]);

		view.setContent(logView.view(
			new String[] { "timestamp", new SimpleDateFormat("dd-MM-yyyy HH:mm (z)").format(new Date(time)) },
			new String[] { "log_id", "0x" + Long.toHexString(time) },
			new String[] { "size", Pancake.formatSize(logData.length) },
			new String[] { "content", new String(logData, StandardCharsets.UTF_8) }
		));

		return view.view("Logs");
	}

}
