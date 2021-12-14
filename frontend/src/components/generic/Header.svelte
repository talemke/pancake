<script lang="ts">
	import { link } from "svelte-spa-router";
	import { Config } from "../../logic/Pancake";
</script>

<main>
	<div class="header">

		<a class="header-title-link" href="/" use:link>
			<h1 class="header-title">{Config.brand_name}</h1>
		</a>

		<input class="header-search" type="text" placeholder="Search..." />

		<div class="spacer" />

	</div>
</main>

<style lang="css">
	.header {
		padding: var(--sm3) var(--md3);
		background-color: var(--header-background);
		display: flex;
	}

	.header-title-link {
		margin-right: var(--md3);

		color: var(--header-color-title);
		text-decoration: none;

		-webkit-transition: color 0.15s;
		transition: color 0.15s;
	}

	.header-title-link:hover {
		color: var(--header-color-title-hover);
	}

	.header-title {
		padding: 0;
		margin: 0;
		font-size: 1.5rem;
		font-weight: 500;
	}

	.header-search {
		padding: 0 var(--sm3);
		background-color: var(--header-search-background);
		font-size: 1rem;
		font-weight: 600;
		color: var(--header-search-color);

		border: var(--header-search-border);
		border-radius: var(--border-radius);
		outline: none;

		-webkit-transition: flex-grow 0.3s, background-color 0.3s, color 0.3s;
		transition: flex-grow 0.3s, background-color 0.3s, color 0.3s;
	}

	.header-search:hover {
		background-color: var(--header-search-background-hover);
	}

	.header-search:focus {
		flex-grow: 1;
		background-color: var(--header-search-background-focus);
		color: var(--header-search-color-focus);
	}

	.spacer {
		flex-grow: 1;
	}
</style>
