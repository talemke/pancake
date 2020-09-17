package net.tassia.pancake.smtp.subethamail;

import net.tassia.pancake.orm.Email;
import net.tassia.pancake.Pancake;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

class PancakeMessageHandlerFactory implements MessageHandlerFactory {
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

			email.setType(Pancake.TYPE_DEFAULT);

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

			if (pancake.getSMTP().incomingEmail(email)) {
				pancake.getLogger().info("Successfully processed email " + email.getUUID().toString() + ".");
			} else {
				pancake.getLogger().info("Failed to process email " + email.getUUID().toString() + ".");
			}
		}

	}

}
