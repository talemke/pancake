<script lang="ts">
	import MdClose from 'svelte-icons/md/MdClose.svelte'

	export let type: "none" | "error" = "error";
	export let closeable: boolean = true;

	let element: HTMLElement;

	function close() {
		element.remove();
	}
</script>

<main class="alert alert-{type}" bind:this={element}>
	<div class="alert-content">
		<slot />
	</div>
	{#if closeable}
		<button class="alert-close" on:click={close}>
			<MdClose />
		</button>
	{/if}
</main>

<style lang="css">
	.alert {
		background-color: var(--alert-background);
		color: var(--alert-color);
		padding: var(--sm3);
		border-radius: var(--border-radius);

		display: flex;
		align-items: center;
	}

	.alert-content {
		flex-grow: 1;
	}

	.alert-close {
		height: 24px;
		padding: 0;
		margin: -24px 0;

		background-color: transparent;
		outline: none;
		border: none;
		color: #5f5f5f;
		cursor: pointer;

		-webkit-transition: color 0.15s, transform 0.15s;
		transition: color 0.15s, transform 0.15s;
	}

	.alert-close:hover {
		color: #9f9f9f;
		transform: rotateZ(90deg);
	}

	.alert-error {
		box-shadow: 0 0 4px var(--danger), inset 0 0 0 1px var(--danger-dark);
	}
</style>
