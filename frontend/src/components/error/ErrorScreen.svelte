<script lang="ts">
</script>

<main>
	<div class="full-screen center error-screen">
		<slot />
	</div>
</main>

<style lang="css">
	.error-screen {
		background-color: var(--primary);
		background-image: linear-gradient(30deg, red, orange);
	}
</style>
