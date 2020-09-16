package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.PancakeHttpServer;
import net.tassia.pancake.orm.Email;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class InboxRoutes {
	private final SimpleDateFormat format;

	/* Constructor */
	public InboxRoutes() {
		this.format = new SimpleDateFormat("dd.MM.yyyy");
	}
	/* Constructor */



	/* Generate View */
	protected void addSideNav(GenericPancakeView view, int current) {
		view.addSideNavRaw(""); // TODO: Compose button
		view.addSideNavHR();
		view.addSideNav("/inbox", "Inbox", "fas fa-inbox", false);
		view.addSideNav("/inbox/drafts", "Drafts", "fas fa-file-alt", false);
		view.addSideNav("/inbox/sent", "Sent", "fas fa-share", false);
		view.addSideNav("/inbox/trash", "Trash", "fas fa-trash", false);
		view.addSideNav("/inbox/spam", "Spam", "fas fa-ban", false);
		view.addSideNavHR();
		view.addSideNav("/inbox/1", "Inbox 1", "fas fa-folder", false);
		view.addSideNav("/inbox/2", "Inbox 2", "fas fa-folder", false);
		view.addSideNav("/inbox/3", "Inbox 3", "fas fa-folder", false);
	}

	protected void addMailNav(GenericPancakeView view, Collection<Email> mails, Email focus) {
		for (Email email : mails) {
			boolean active = focus != null && focus.getUUID().equals(email.getUUID());
			String description = email.getSender() + " - " + format.format(new Date(email.getTimestamp()));
			view.addMailNav("/mail/" + email.getUUID().toString(), "N/A", description, active); // TODO: Subject
		}
	}
	/* Generate View */



	/* Register Routes */
    public void registerRoutes(PancakeHttpServer server) {

		// Inbox
		server.GET("\\/inbox", new InboxRoute(this));

		// Mail
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX, new MailRoute(this));
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX + "\\/print", new MailPrintRoute());
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX + "\\/source", new MailSourceRoute(this));

    }
	/* Register Routes */

}
