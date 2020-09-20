package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PancakeCLI {
	private final ExecutorService service;
	private final CLIReader reader;

	/* Constructor */
	public PancakeCLI(Pancake pancake) {
		this.reader = new CLIReader(pancake);
		this.service = Executors.newSingleThreadExecutor();

		reader.commands.put("accounts", new CMD_Accounts(pancake));
		reader.commands.put("sessions", new CMD_Sessions(pancake));
	}
	/* Constructor */



	/* Start & Stop */
	public void start() {
		service.submit(reader);
	}

	public void stop() {
		reader.scanner.close();
		service.shutdownNow();
	}
	/* Start & Stop */

}
