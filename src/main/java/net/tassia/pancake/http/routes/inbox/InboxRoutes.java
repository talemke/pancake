package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.PancakeHttpServer;
import net.tassia.pancake.orm.Email;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class InboxRoutes {
	protected static final int INBOX_DEFAULT = 0;
	protected static final int INBOX_DRAFTS = 1;
	protected static final int INBOX_SENT = 2;
	protected static final int INBOX_TRASH = 3;
	protected static final int INBOX_SPAM = 4;
	protected static final int INBOX_CUSTOM = 5;
	private final HttpView composeButton;
	private final SimpleDateFormat format;

	/* Constructor */
	public InboxRoutes() {
		this.composeButton = new HttpView("/views/misc/compose_button.html");
		this.format = new SimpleDateFormat("dd.MM.yyyy");
	}
	/* Constructor */



	/* Generate View */
	protected void addSideNav(GenericPancakeView view, int current) {
		view.addSideNavRaw(composeButton.view());
		view.addSideNavHR();
		view.addSideNav("/inbox", "Inbox", "fas fa-inbox", current == INBOX_DEFAULT);
		view.addSideNav("/inbox/drafts", "Drafts", "fas fa-file-alt", current == INBOX_DRAFTS);
		view.addSideNav("/inbox/sent", "Sent", "fas fa-share", current == INBOX_SENT);
		view.addSideNav("/inbox/trash", "Trash", "fas fa-trash", current == INBOX_TRASH);
		view.addSideNav("/inbox/spam", "Spam", "fas fa-ban", current == INBOX_SPAM);
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

		// Inboxes
		server.GET("\\/inbox", new GET_Inbox(this));
		server.GET("\\/inbox\\/drafts", new GET_Drafts(this));
		server.GET("\\/inbox\\/sent", new GET_Sent(this));
		server.GET("\\/inbox\\/trash", new GET_Trash(this));
		server.GET("\\/inbox\\/spam", new GET_Spam(this));

		// Mail
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX, new GET_Mail(this));
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX + "\\/print", new MailPrintRoute());
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX + "\\/source", new MailSourceRoute(this));

    }
	/* Register Routes */

}
