<script lang="ts">
	import Footer from "./Footer.svelte";
	import Header from "./Header.svelte";
</script>

<main>

	<Header />
	<div class="page-content">
		<slot />
	</div>
	<Footer />

</main>

<style lang="css">
	.page-content {
		margin: var(--lg1) var(--lg3) 0 var(--lg3);
	}
</style>
