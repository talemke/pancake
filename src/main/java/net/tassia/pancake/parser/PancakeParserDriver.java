package net.tassia.pancake.parser;

public interface PancakeParserDriver {

	ParsedMail parse(byte[] data);
	String getName();
	String getVersion();

}
