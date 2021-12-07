<script lang="ts">
</script>

<main>
	<div class="full-screen center auth-screen">
		<slot />
	</div>
</main>

<style lang="css">
	.auth-screen {
		background-color: var(--primary);
		background-image: linear-gradient(30deg, var(--primary), var(--secondary));
	}
</style>
