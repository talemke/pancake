package net.tassia.pancake.http.views;

import net.tassia.pancake.http.HttpView;

import java.nio.charset.StandardCharsets;

public class SideNavView extends HttpView {

	public SideNavView() {
		super("/views/sidenav_entry.html");
	}

	public String getView(String link, String name, String icon, boolean active) {
		return view(
			new String[] { "link", link },
			new String[] { "name", name },
			new String[] { "icon", icon },
			new String[] { "active", active ? "sidenav-button-active" : "" }
		);
	}

	public byte[] getViewData(String link, String name, String icon, boolean active) {
		return getView(link, name, icon, active).getBytes(StandardCharsets.UTF_8);
	}

	public String getHR() {
		return "<hr>";
	}

	public byte[] getHRData() {
		return getHR().getBytes(StandardCharsets.UTF_8);
	}

}
