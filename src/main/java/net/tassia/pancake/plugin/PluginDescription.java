package net.tassia.pancake.plugin;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class PluginDescription {

	@NotNull
	public final String identifier;

	@NotNull
	public final String name;

	@NotNull
	public final String version;

	@NotNull
	public final String description;

	@NotNull
	public final List<String> authors;

	@NotNull
	public final String website;

	@NotNull
	public final Collection<PluginDependency> dependencies;

	@NotNull
	public final Collection<PluginDependency> softDependencies;



	public PluginDescription(@NotNull String identifier, @NotNull String name, @NotNull String version,
							 @NotNull String description, @NotNull List<String> authors, @NotNull String website,
							 @NotNull Collection<PluginDependency> dependencies,
							 @NotNull Collection<PluginDependency> softDependencies) {
		this.identifier = identifier;
		this.name = name;
		this.version = version;
		this.description = description;
		this.authors = authors;
		this.website = website;
		this.dependencies = dependencies;
		this.softDependencies = softDependencies;
	}

}
