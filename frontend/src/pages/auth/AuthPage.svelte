<script lang="ts">
	import Router from "svelte-spa-router";
	import Error404 from "../error/Error404.svelte";
	import AuthLoginPage from "./AuthLoginPage.svelte";

	const routes = {
		'/auth/login': AuthLoginPage,
		'*': Error404,
	};
</script>

<main>
	<Router {routes} />
</main>
