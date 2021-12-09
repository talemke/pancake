<script lang="ts">
import LoadingSpinner from "../../components/feedback/LoadingSpinner.svelte";
import {API} from "../../logic/Pancake";
</script>

<main>

	<h1>Terms of Service</h1>

	<p>
		{#await API.FetchTermsOfService()}
			<LoadingSpinner />
		{:then content}
			{content}
		{:catch error}
			<!-- TODO -->
		{/await}
	</p>

</main>

<style lang="css">
	h1 {
		border-bottom: var(--border-light);
		padding-bottom: var(--sm3);
		margin-bottom: var(--sm3);
	}
</style>
