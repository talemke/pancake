package net.tassia.pancake.spam;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.spam.generic.GenericSpamFilter;

import java.util.ArrayList;
import java.util.Collection;

public class PancakeSpam {
	private final Pancake pancake;
	private final Collection<PancakeSpamDriver> drivers;

	public PancakeSpam(Pancake pancake) {
		this.pancake = pancake;
		this.drivers = new ArrayList<>();

		// Register spam filters
		this.drivers.add(new GenericSpamFilter());
	}

	public Action filter(Email email) {
		pancake.getLogger().info("Spam-Filtering email " + email.getUUID() + "...");

		SpamFilterResult result = new SpamFilterResult();
		for (PancakeSpamDriver driver : drivers) {
			pancake.getLogger().fine("- Filtering with " + driver.getName() + " (v" + driver.getVersion() + ")...");
			SpamFilterResult driverResult = driver.filter(email);
			if (driverResult == null) continue;
			if (result.probability < driverResult.probability) {
				result = driverResult;
			}
			if (result.probability == 1) break;
		}
		Action action = determineActionFor(result.probability);

		String perc = (result.probability * 100) + "% spam";
		if (result.reason != null) perc = perc + " (" + result.reason + ")";
		pancake.getLogger().info("Filtered " + email.getUUID() + ": " + perc + " => " + action.name());
		return action;
	}

	public void learn(Email email, SpamFilterResult result) {
		pancake.getLogger().info("Learning email " + email.getUUID() + "...");
		for (PancakeSpamDriver driver : drivers) {
			pancake.getLogger().fine("- Filtering with " + driver.getName() + " (v" + driver.getVersion() + ")...");
			driver.learn(email, result);
		}
		pancake.getLogger().info("Learned email " + email.getUUID() + "!");
	}

	public Action determineActionFor(double probability) {
		if (probability >= 1) {
			return Action.REJECT;
		} else if (probability >= 0.5) {
			return Action.SPAM;
		} else if (probability > 0) {
			return Action.FLAG;
		} else {
			return Action.PASS;
		}
	}



	public static class SpamFilterResult {
		public String reason;
		public double probability;

		public SpamFilterResult() {
			this(null, 0D);
		}

		public SpamFilterResult(String reason, double probability) {
			this.reason = reason;
			this.probability = probability;
		}
	}

	public enum Action {
		PASS, FLAG, SPAM, REJECT
	}

}
