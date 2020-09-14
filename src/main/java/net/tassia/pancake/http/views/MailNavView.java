package net.tassia.pancake.http.views;

import net.tassia.pancake.http.HttpView;

import java.nio.charset.StandardCharsets;

public class MailNavView extends HttpView {

	public MailNavView() {
		super("/views/mailnav_entry.html");
	}

	public String getView(String link, String title, String description, boolean active) {
		return view(
			new String[] { "link", link },
			new String[] { "title", title },
			new String[] { "description", description },
			new String[] { "active", active ? "mailnav-button-active" : "" }
		);
	}

	public byte[] getViewData(String link, String title, String description, boolean active) {
		return getView(link, title, description, active).getBytes(StandardCharsets.UTF_8);
	}

}
