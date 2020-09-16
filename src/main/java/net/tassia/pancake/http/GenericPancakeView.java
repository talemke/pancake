package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.orm.Inbox;

import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

public class GenericPancakeView {
	protected static final HttpView INDEX_VIEW = new HttpView("/views/index.html");
	protected static final SideNavView SIDE_NAV_VIEW = new SideNavView();
	protected static final MailNavView MAIL_NAV_VIEW = new MailNavView();
	protected static final String ALERT_ROOT_USER = new HttpView("/views/alerts/root_user.html").view();
	private final Pancake pancake;
	private final HttpRequest request;
	private String alerts;
	private String sideNav;
	private String mailNav;
	private String content;

	/* Constructor */
	public GenericPancakeView(Pancake pancake, HttpRequest request) {
		this.pancake = pancake;
		this.request = request;
		this.alerts = "";
		this.sideNav = "";
		this.mailNav = "";
		this.content = "";

		if (request.getAuth() != null && request.getAuth().getUUID().equals(Account.ROOT.getUUID())) {
			alerts += ALERT_ROOT_USER;
		}
	}
	/* Constructor */



	/* Access */
	public boolean checkAccess() {
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return true;
		} else {
			return false;
		}
	}
	/* Access */



	/* Alerts */
	// TODO: Root User alert

	public void clearAlerts() {
		alerts = "";
	}
	/* Alerts */



	/* Side Navigation */
	public void addSideNav(String link, String name, String icon, boolean active) {
		sideNav += SIDE_NAV_VIEW.getView(link, name, icon, active);
	}

	public void addSideNavHR() {
		sideNav += "<hr>";
	}

	public void addSideNavRaw(String raw) {
		sideNav += raw;
	}

	public void clearSideNav() {
		sideNav = "";
	}
	/* Side Navigation */



	/* Mail Navigation */
	public void addMailNav(String link, String title, String description, boolean active) {
		mailNav += MAIL_NAV_VIEW.getView(link, title, description, active);
	}

	public void clearMailNav() {
		mailNav = "";
	}
	/* Mail Navigation */



	/* Content */
	public void setContent(String content) {
		this.content = content;
	}
	/* Content */



	/* View */
	public byte[] view(String title) {
		return INDEX_VIEW.viewData(
			new String[] { "window_title", title + " | " + pancake.getConfig().brandName },
			new String[] { "brand_name", pancake.getConfig().brandName },
			new String[] { "sidenav", sideNav },
			new String[] { "mailnav", mailNav },
			new String[] { "content", content },
			new String[] { "username", request.checkAuth() ? request.getAuth().getName() : "guest" },
			new String[] { "alerts", alerts }
		);
	}
	/* View */



	/* Resources */
	public Account findAccount(String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		Account acc = pancake.getAccount(u);
		if (acc == null) {
			request.setErrorPage(404);
			return null;
		}
		return acc;
	}

	public Group findGroup(String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		Group g = pancake.getGroup(u);
		if (g == null) {
			request.setErrorPage(404);
			return null;
		}
		return g;
	}

	public Email findEmail(String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		try {
			Email email = pancake.getDatabase().fetchEmail(u);
			if (email == null) {
				request.setErrorPage(404);
				return null;
			}
			return email;
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findEmails(Account account, Inbox inbox, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchEmails(account, inbox, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findDraftEmails(Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchDraftEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findSentEmails(Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchSentEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findDeletedEmails(Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchDeletedEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findSpamEmails(Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchSpamEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}
	/* Resources */

}
