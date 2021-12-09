<script lang="ts">
	import Page from "../../components/generic/Page.svelte";
	import {API} from "../../logic/Pancake";
	import LoadingSpinner from "../../components/feedback/LoadingSpinner.svelte";
	import Alert from "../../components/alert/Alert.svelte";
	import TermsOfService from "./TermsOfService.svelte";
</script>

<Page>
	<h1>Terms of Service</h1>

	<p>
		{#await API.FetchTermsOfService()}
			<LoadingSpinner />
		{:then content}
			<TermsOfService>{content}</TermsOfService>
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
