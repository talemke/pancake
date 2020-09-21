package net.tassia.pancake.parser;

import java.util.ArrayList;
import java.util.List;

public class ParsedMail {

	public String subject = null;
	public String display = null;
	public List<Attachment> attachments = new ArrayList<>();

	public static class Attachment {
		public String name = null;
		public String contentType = null;
		public byte[] data = null;
	}

}
