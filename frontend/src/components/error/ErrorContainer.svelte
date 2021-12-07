<script lang="ts">
</script>

<main>
	<div class="error-container">
		<slot />
	</div>
</main>

<style lang="css">
	.error-container {
		background-color: white;
		box-shadow: 0 0 8px rgba(0, 0, 0, 0.25);
		padding: var(--lg3);
		border-radius: var(--sm3);
		min-width: 30vw;
		text-align: center;
	}
</style>
