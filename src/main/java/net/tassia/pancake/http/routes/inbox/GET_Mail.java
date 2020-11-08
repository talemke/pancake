package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.orm.Mail;
import net.tassia.pancake.parser.ParsedMail;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

class GET_Mail implements HttpRoute {
	private final InboxRoutes routes;
	private final HttpView mailView;
	private final HttpView attachmentsView;
	private final HttpView attachmentView;
	private final SimpleDateFormat format;
	private final String alertMailDeleted;
	private final String alertMailSpam;

	public GET_Mail(InboxRoutes routes) {
		this.routes = routes;
		this.mailView = new HttpView("/views/email/email.html");
		this.attachmentsView = new HttpView("/views/email/attachments.html");
		this.attachmentView = new HttpView("/views/email/attachment.html");
		this.format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.alertMailDeleted = new HttpView("/views/alerts/mail_deleted.html").view();
		this.alertMailSpam = new HttpView("/views/alerts/mail_spam.html").view();
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Collection<Mail> mail = pancake.getHTTP().getResources().findEmails(request, request.getAuth(), null, 0, 0);
		if (mail == null) return null;

		Mail focus = pancake.getHTTP().getResources().findEmail(request, request.getAuth(), matches[0]);
		if (focus == null) return null;
		if (focus.getParsed() == null) {
			request.setErrorPage(500);
			return null;
		}

		String alerts = "";
		int inbox;

		switch (focus.getType()) {
			case Pancake.TYPE_DRAFT:
				inbox = InboxRoutes.INBOX_DRAFTS;
				break;
			case Pancake.TYPE_SENT:
				inbox = InboxRoutes.INBOX_SENT;
				break;
			case Pancake.TYPE_DELETED:
				inbox = InboxRoutes.INBOX_TRASH;
				alerts += alertMailDeleted;
				break;
			case Pancake.TYPE_SPAM:
				inbox = InboxRoutes.INBOX_SPAM;
				alerts += alertMailSpam;
				break;
			case Pancake.TYPE_DEFAULT:
			default:
				inbox = InboxRoutes.INBOX_DEFAULT;
				break;
		}

		routes.addSideNav(view, inbox);
		routes.addMailNav(view, mail, focus);

		String content = focus.getParsed().display;
		if (content == null) content = new String(focus.getData(), StandardCharsets.UTF_8);

		String attachments = "";
		if (!focus.getParsed().attachments.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < focus.getParsed().attachments.size(); i++) {
				ParsedMail.Attachment att = focus.getParsed().attachments.get(i);
				String name = att.name != null ? att.name : "Unknown Name"; // TODO: Maybe just hide this attachment completely?
				sb.append(", ").append(attachmentView.view(
					new String[]{"attachment_url", "/mail/" + focus.getUUID() + "/attachment/" + i},
					new String[]{"attachment_name", name}
				));
			}
			attachments = attachmentsView.view(
				new String[] { "attachments", sb.substring(2) }
			);
		}

		view.setContent(mailView.view(
			new String[] { "mail_alerts", alerts },
			new String[] { "mail_id", focus.getUUID().toString() },
			new String[] { "mail_subject", focus.getParsed().subject },
			new String[] { "mail_date", format.format(new Date(focus.getTimestamp())) },
			new String[] { "mail_size", Pancake.formatSize(focus.getData().length) },
			new String[] { "mail_sender", focus.getSender() },
			new String[] { "mail_recipient", focus.getRecipient() },
			new String[] { "mail_content", content },
			new String[] { "attachments", attachments }
		));

		return view.view("Inbox");
	}

}
