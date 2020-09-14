package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.PancakeHttpServer;
import net.tassia.pancake.orm.Email;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class InboxRoutes {
	private final HttpView view;
	private final HttpView mailNavView;
	private final HttpView mailView;
	private final SimpleDateFormat format1, format2;

	/* Constructor */
	public InboxRoutes() {
		this.view = new HttpView("/views/inbox/index.html");
		this.mailNavView = new HttpView("/views/inbox/mail_nav_entry.html");
		this.mailView = new HttpView("/views/inbox/email.html");
		this.format1 = new SimpleDateFormat("dd.MM.yyyy");
		this.format2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	}
	/* Constructor */



	/* Generate View */
	protected byte[] generateView(Pancake pancake, HttpRequest request, Collection<Email> list, Email focus) {
		String mailNav = "";
		for (Email email : list) {
			boolean isFocussed = focus != null && focus.getUUID().equals(email.getUUID());
			mailNav += mailNavView.view(
				new String[] { "mail_id", email.getUUID().toString() },
				new String[] { "mail_subject", "N/A" },
				new String[] { "mail_author", email.getSender() },
				new String[] { "mail_date", format1.format(new Date(email.getTimestamp())) },
				new String[] { "mail_active", isFocussed ? "mailnav-button-active" : "" }
			);
		}

		String mail;
		if (focus != null) {
			mail = mailView.view(
				new String[] { "mail_id", focus.getUUID().toString() },
				new String[] { "mail_subject", "N/A" },
				new String[] { "mail_date", format2.format(new Date(focus.getTimestamp())) },
				new String[] { "mail_size", focus.getData().length + " bytes" },
				new String[] { "mail_sender", focus.getSender() },
				new String[] { "mail_recipient", focus.getRecipient() },
				new String[] { "mail_content", new String(focus.getData(), StandardCharsets.UTF_8) }
			);
		} else {
			mail = "<span class=\"pos-absolute pos-center\">No mail selected.</span>";
		}

		return view.viewData(
			new String[] { "username", request.getAuth().getName() },
			new String[] { "mail_nav", mailNav },
			new String[] { "email", mail }
		);
	}
	/* Generate View */



	/* Register Routes */
    public void registerRoutes(PancakeHttpServer server) {

		// Inbox
		server.GET("\\/inbox", new InboxRoute(this));

		// Mail
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX, new MailRoute(this));
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX + "\\/print", new MailPrintRoute(this));
		server.GET("\\/mail\\/" + Pancake.UUID_REGEX + "\\/source", new MailSourceRoute(this));

    }
	/* Register Routes */

}
