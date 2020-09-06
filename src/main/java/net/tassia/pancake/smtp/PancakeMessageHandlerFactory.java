package net.tassia.pancake.smtp;

import net.tassia.pancake.orm.Email;
import net.tassia.pancake.Pancake;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class PancakeMessageHandlerFactory implements MessageHandlerFactory {
	private final Pancake pancake;

	public PancakeMessageHandlerFactory(Pancake pancake) {
		this.pancake = pancake;
	}

	@Override
	public MessageHandler create(MessageContext context) {
		return new Handler(context);
	}



	private class Handler implements MessageHandler {
		private final Email email;

		public Handler(MessageContext context) {
			this.email = new Email();

			email.setTimestamp(System.currentTimeMillis());
			email.setHelo(context.getHelo());
			email.setRemoteAddress(context.getRemoteAddress().toString());
			// TODO: Certificates

			email.setDeleted(false);
			email.setDraft(false);
			email.setOutgoing(false);

			pancake.getLogger().info("Starting receiving email " + email.getUUID().toString() + "...");
		}

		@Override
		public void from(String from) throws RejectException {
			if (from.length() > 255) throw new RejectException("'from' too long (>255)");
			email.setSender(from);
		}

		@Override
		public void recipient(String recipient) throws RejectException {
			if (recipient.length() > 255) throw new RejectException("'recipient' too long (>255)");
			email.setRecipient(recipient);
		}

		@Override
		public void data(InputStream data) throws IOException {
			byte[] buf = data.readAllBytes();
			email.setData(buf);
		}

		@Override
		public void done() {
			pancake.getLogger().info("Storing email " + email.getUUID().toString() + "...");

			// TODO: Attachments

			email.setAccount(pancake.getAccountByEmailName(email.getRecipient().split("@", 2)[0]));

			try {
				pancake.getDatabase().storeEmail(email);
			} catch (SQLException ex) {
				ex.printStackTrace();
				return;
			}

			pancake.getLogger().info("Successfully processed email " + email.getUUID().toString() + ".");
		}

	}

}
