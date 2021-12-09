<script lang="ts">
	import Router from "svelte-spa-router";
	import Error404 from "../error/Error404.svelte";
	import TermsOfServicePage from "./TermsOfServicePage.svelte";
	import CookiesPage from "./CookiesPage.svelte";
	import PrivacyPage from "./PrivacyPage.svelte";

	const routes = {
		'/legal/cookies': CookiesPage,
		'/legal/privacy': PrivacyPage,
		'/legal/tos': TermsOfServicePage,
		'*': Error404,
	};
</script>

<main>
	<Router {routes} />
</main>
