package net.tassia.pancake.plugin;

import net.tassia.pancake.VersionRequirement;

public final class PluginDependency {

	public final String plugin;

	public final VersionRequirement version;



	public PluginDependency(String plugin, VersionRequirement version) {
		this.plugin = plugin;
		this.version = version;
	}

}
