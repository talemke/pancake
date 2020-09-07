package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class HttpViewRoute implements HttpRoute {
	private final String view;

	public HttpViewRoute(String view) {
		byte[] viewData;
		try {
			viewData = Pancake.class.getResourceAsStream(view).readAllBytes();
		} catch (IOException ex) {
			throw new Error("Failed to load view: " + view, ex);
		}
		this.view = new String(viewData, StandardCharsets.UTF_8);
	}

	protected byte[] view(String[]...replacements) {
		String str = view;
		for (String[] replacement : replacements) {
			str = str.replaceAll("{! " + replacement[0] + " !}", replacement[1]);
			str = str.replaceAll("{{ " + replacement[0] + " }}", PancakeHTTP.escapeXSS(replacement[1]));
		}
		return str.getBytes(StandardCharsets.UTF_8);
	}

}
