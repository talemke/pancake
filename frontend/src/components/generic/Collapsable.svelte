<script lang="ts">
	export let show: boolean = false;

	$: className = show ? "collapse show" : "collapse";
</script>

<main>
	<div class={className}>
		{#if show}
			<slot />
		{/if}
	</div>
</main>
