<script lang="ts">
	import Router from "svelte-spa-router";
	import AuthPage from "./pages/auth/AuthPage.svelte";
	import Error404 from "./pages/error/Error404.svelte";

	const routes = {
		"/": Error404, // TODO
		"/auth/:page": AuthPage,
		"*": Error404,
	};
</script>

<main>
	<Router {routes} />
</main>
