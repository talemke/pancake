package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpViewRoute;
import net.tassia.pancake.orm.Email;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

class MailPrintRoute extends HttpViewRoute {
	private final SimpleDateFormat format;

	public MailPrintRoute() {
		super("/views/print.html");
		this.format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss (z)");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}


		// Parse UUID
		UUID uuid;
		try {
			uuid = UUID.fromString(matches[0]);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}


		// Fetch email
		Email email;
		try {
			email = pancake.getDatabase().fetchEmail(uuid);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
		if (email == null) {
			request.setErrorPage(404);
			return null;
		}


		// Parse variables
		String subject = "N/A"; // TODO
		String timestamp = format.format(new Date(email.getTimestamp())) + " (0x" + Long.toHexString(email.getTimestamp()) + ")";
		String sender = email.getSender();
		String recipient = email.getRecipient();
		String cc = ""; // TODO
		String message = new String(email.getData(), StandardCharsets.UTF_8);
		String mailId = email.getUUID().toString();
		String account = email.getAccount().getUUID().toString();
		String version = String.format("v%d.%d.%d, build %d - %s @ %s", Pancake.VERSION_MAJOR, Pancake.VERSION_MINOR,
			Pancake.VERSION_PATCH, Pancake.VERSION_BUILD, Pancake.VERSION_BRANCH, Pancake.VERSION_HEAD);


		// Echo email
		return view(
			new String[] { "subject", subject },
			new String[] { "timestamp", timestamp },
			new String[] { "sender", sender },
			new String[] { "recipient", recipient },
			new String[] { "cc", cc },
			new String[] { "message", message },
			new String[] { "mail_id", mailId },
			new String[] { "account", account },
			new String[] { "pancake_version", version }
		);
	}

}
