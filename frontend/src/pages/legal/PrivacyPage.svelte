<script lang="ts">
	import Page from "../../components/generic/Page.svelte";
	import {API} from "../../logic/Pancake";
	import LoadingSpinner from "../../components/feedback/LoadingSpinner.svelte";
	import Alert from "../../components/alert/Alert.svelte";
	import Privacy from "./Privacy.svelte";
</script>

<Page>
	<h1>Privacy</h1>

	<p>
		{#await API.FetchCookies()}
			<LoadingSpinner />
		{:then content}
			<Privacy>{content}</Privacy>
		{:catch error}
			<Alert type="error">{error}</Alert>
		{/await}
	</p>
</Page>

<style lang="css">
	h1 {
		border-bottom: var(--border-light);
		padding-bottom: var(--sm3);
		margin-bottom: var(--sm3);
	}
</style>
