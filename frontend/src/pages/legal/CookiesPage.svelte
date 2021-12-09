<script lang="ts">
	import Page from "../../components/generic/Page.svelte";
	import {API} from "../../logic/Pancake";
	import LoadingSpinner from "../../components/feedback/LoadingSpinner.svelte";
	import Alert from "../../components/alert/Alert.svelte";
	import Cookies from "./Cookies.svelte";
</script>

<Page>
	<h1>Cookies</h1>

	<p>
		{#await API.FetchCookies()}
			<LoadingSpinner />
		{:then content}
			<Cookies>{content}</Cookies>
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
