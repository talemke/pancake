<script lang="ts">
	import { link } from "svelte-spa-router";

	export let href: string;
</script>

{#if href.startsWith("https://")}
	<a href={href} class="link" target="_blank" rel="noopener noreferrer">
		<slot />
	</a>
{:else if href.startsWith("/")}
	<a href={href} use:link class="link">
		<slot />
	</a>
{/if}

<style lang="css">
	.link {
		color: var(--link-color);
		text-decoration: none;

		-webkit-transition: color 0.15s;
		transition: color 0.15s;
	}

	.link:hover {
		color: var(--link-color-hover);
	}
</style>
