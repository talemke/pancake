<script lang="ts">
	import Router from "svelte-spa-router";
	import AuthPage from "./pages/auth/AuthPage.svelte";
	import Error404 from "./pages/error/Error404.svelte";
	import IndexPage from "./pages/user/IndexPage.svelte";
	import LegalPage from "./pages/legal/LegalPage.svelte";

	const routes = {

		"/": IndexPage,

		"/auth/:page": AuthPage,
		"/legal/:page": LegalPage,

		"*": Error404,

	};
</script>

<main>
	<Router {routes} />
</main>
