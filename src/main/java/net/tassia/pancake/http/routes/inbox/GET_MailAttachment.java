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

class GET_MailAttachment implements HttpRoute {
	private final InboxRoutes routes;
	private final HttpView mailView;
	private final HttpView attachmentsView;
	private final HttpView attachmentView;
	private final SimpleDateFormat format;
	private final String alertMailDeleted;
	private final String alertMailSpam;

	public GET_MailAttachment(InboxRoutes routes) {
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


		Mail focus = pancake.getHTTP().getResources().findEmail(request, request.getAuth(), matches[0]);
		if (focus == null) return null;
		if (focus.getParsed() == null) {
			request.setErrorPage(500);
			return null;
		}


		int index;
		try {
			index = Integer.parseInt(matches[1]);
		} catch (NumberFormatException ex) {
			request.setErrorPage(404);
			return null;
		}


		ParsedMail.Attachment att;
		try {
			att = focus.getParsed().attachments.get(index);
		} catch (IndexOutOfBoundsException ex) {
			request.setErrorPage(404);
			return null;
		}
		if (att == null) {
			request.setErrorPage(404);
			return null;
		}


		request.setContentType(att.contentType);
		request.setResponseHeader("Content-Disposition", "inline; filename=\"" + att.name + "\"");
		return att.data;
	}

}
