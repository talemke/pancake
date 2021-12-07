<script lang="ts">
	import Router from "svelte-spa-router";
	import AuthPage from "./pages/auth/AuthPage.svelte";
	import Error404 from "./pages/error/Error404.svelte";
	import IndexPage from "./pages/user/IndexPage.svelte";

	const routes = {
		"/": IndexPage,
		"/auth/:page": AuthPage,
		"*": Error404,
	};
</script>

<main>
	<Router {routes} />
</main>
