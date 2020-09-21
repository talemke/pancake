package net.tassia.pancake.parser.commons;

import net.tassia.pancake.parser.PancakeParserDriver;
import net.tassia.pancake.parser.ParsedMail;
import org.apache.commons.mail.util.MimeMessageParser;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ApacheCommonsEmailParser implements PancakeParserDriver {

	@Override
	public ParsedMail parse(byte[] data) {
		Session s = Session.getInstance(new Properties());
		InputStream is = new ByteArrayInputStream(data);
		MimeMessage msg;

		try {
			msg = new MimeMessage(s, is);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return null;
		}

		MimeMessageParser parser = new MimeMessageParser(msg);
		try {
			parser.parse();
			ParsedMail pm = new ParsedMail();

			pm.subject = parser.getSubject();
			pm.display = parser.getHtmlContent();
			if (pm.display == null) pm.display = parser.getPlainContent();

			for (DataSource ds : parser.getAttachmentList()) {
				ParsedMail.Attachment att = new ParsedMail.Attachment();
				att.name = ds.getName();
				att.contentType = ds.getContentType();
				att.data = ds.getInputStream().readAllBytes();
				pm.attachments.add(att);
			}

			return pm;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getName() {
		return "Apache Commons Email";
	}

	@Override
	public String getVersion() {
		return "1.5";
	}

}
