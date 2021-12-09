<script lang="ts">
</script>

<main>
	<div class="auth-container">
		<slot />
	</div>
</main>

<style lang="css">
	.auth-container {
		background-color: white;
		box-shadow: 0 0 8px rgba(0, 0, 0, 0.25);
		padding: var(--lg3);
		border-radius: var(--sm3);
		text-align: center;
	}
</style>
