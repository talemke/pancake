package net.tassia.pancake.smtp.subethamail;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Mail;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.helper.BasicMessageListener;

class MessageHandler implements BasicMessageListener {
	private final Pancake pancake;

	MessageHandler(Pancake pancake) {
		this.pancake = pancake;
	}

	@Override
	public void messageArrived(MessageContext context, String from, String to, byte[] data) throws RejectException {
		Mail mail = new Mail();
		pancake.getLogger().info("Storing email " + mail.getUUID().toString() + "...");

		if (from.length() > 255) {
			throw new RejectException("sender too long (>255)");
		}

		if (to.length() > 255) {
			throw new RejectException("recipient too long (>255)");
		}

		mail.setTimestamp(System.currentTimeMillis());
		mail.setHelo(context.getHelo().orElse(""));
		mail.setRemoteAddress(context.getRemoteAddress().toString());
		// TODO: SessionID
		// TODO: Certificates
		mail.setSender(from);
		mail.setRecipient(to);
		mail.setData(data);
		mail.setType(Pancake.TYPE_DEFAULT);

		if (pancake.getSMTP().incomingEmail(mail)) {
			pancake.getLogger().info("Successfully processed email " + mail.getUUID().toString() + ".");
		} else {
			pancake.getLogger().info("Failed to process email " + mail.getUUID().toString() + ".");
		}
	}

}
