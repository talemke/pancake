package net.tassia.pancake.parser.javamail;

import net.tassia.pancake.parser.PancakeParserDriver;
import net.tassia.pancake.parser.ParsedMail;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Deprecated
public class JavaMailParser implements PancakeParserDriver {

	@Override
	public ParsedMail parse(byte[] data) {
		Session s = Session.getInstance(new Properties());
		InputStream is = new ByteArrayInputStream(data);
		MimeMessage msg;
		try {
			msg = new MimeMessage(s, is);
			ParsedMail m = new ParsedMail();

			m.subject = msg.getSubject();

			String content = new String(data, StandardCharsets.UTF_8);
			m.display = content.replace("\r", "").split("\n\n", 2)[1];

			return m;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getName() {
		return "JavaMail";
	}

	@Override
	public String getVersion() {
		return "1.4.4";
	}

}
