<script lang="ts">
	import ErrorContainer from "../../components/error/ErrorContainer.svelte";
	import ErrorScreen from "../../components/error/ErrorScreen.svelte";
</script>

<main>
	<ErrorScreen>
		<ErrorContainer>

			<h1 class="display-1"><strong>404</strong> Not Found</h1>
			<hr/>

			<h3>Oops! This should not have happened.</h3>

		</ErrorContainer>
	</ErrorScreen>
</main>
