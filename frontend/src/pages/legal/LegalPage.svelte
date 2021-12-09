<script lang="ts">
	import Router from "svelte-spa-router";
	import Error404 from "../error/Error404.svelte";
	import TermsOfServicePage from "./TermsOfServicePage.svelte";

	const routes = {
		'/legal/cookies': TermsOfServicePage, // TODO
		'/legal/privacy': TermsOfServicePage, // TODO
		'/legal/tos': TermsOfServicePage,
		'*': Error404,
	};
</script>

<main>
	<Router {routes} />
</main>
