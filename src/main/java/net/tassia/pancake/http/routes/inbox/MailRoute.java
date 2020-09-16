package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.orm.Email;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

class MailRoute implements HttpRoute {
	private final InboxRoutes routes;
	private final HttpView mailView;
	private final SimpleDateFormat format;

	public MailRoute(InboxRoutes routes) {
		this.routes = routes;
		this.mailView = new HttpView("/views/email.html");
		this.format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Collection<Email> emails = view.findEmails(request.getAuth(), null, 0, 0);
		if (emails == null) return null;

		Email focus = view.findEmail(matches[0]);
		if (focus == null) return null;

		routes.addSideNav(view, InboxRoutes.INBOX_DEFAULT);
		routes.addMailNav(view, emails, focus);

		view.setContent(mailView.view(
			new String[] { "mail_id", focus.getUUID().toString() },
			new String[] { "mail_subject", "N/A" }, // TODO: Subject
			new String[] { "mail_date", format.format(new Date(focus.getTimestamp())) },
			new String[] { "mail_size", focus.getData().length + " bytes" },
			new String[] { "mail_sender", focus.getSender() },
			new String[] { "mail_recipient", focus.getRecipient() },
			new String[] { "mail_content", new String(focus.getData(), StandardCharsets.UTF_8) }
		));

		return view.view("Inbox");
	}

}
