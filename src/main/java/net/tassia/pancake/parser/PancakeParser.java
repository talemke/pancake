package net.tassia.pancake.parser;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.parser.commons.ApacheCommonsEmailParser;

public class PancakeParser {
	private final Pancake pancake;
	private final PancakeParserDriver driver;

	public PancakeParser(Pancake pancake) {
		this.pancake = pancake;
		this.driver = new ApacheCommonsEmailParser();

		pancake.getLogger().info("  Driver: " + driver.getName() + " - v" + driver.getVersion());
	}

	public ParsedMail parse(byte[] data) {
		return driver.parse(data);
	}

}
