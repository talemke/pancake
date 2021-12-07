<script lang="ts">
	import AuthContainer from "../../components/auth/AuthContainer.svelte";
	import AuthScreen from "../../components/auth/AuthScreen.svelte";
	import LoadingSpinner from "../../components/feedback/LoadingSpinner.svelte";
	import Collapsable from "../../components/generic/Collapsable.svelte";
	import Button from "../../components/input/Button.svelte";
	import TextInput from "../../components/input/TextInput.svelte";
	import { API, Format, HandleError, Language } from "../../logic/Pancake";

	let showLoading: boolean = false;
	let showForm: boolean = true;

	let formUsername: string;
	let formPassword: string;

	function setLoading(loading: boolean) {
		showLoading = loading;
		showForm = !loading;
	}

	function handleLogin() {
		// Set to loading
		setLoading(true);

		// Unset loading
		API().AuthLogin(formUsername, formPassword)
			.catch(HandleError)
			.then(() => {
				// TODO
				setLoading(false);
			});
	}
</script>

<main>
	<AuthScreen>
		<AuthContainer>

			<Collapsable bind:show={showLoading}>
				<LoadingSpinner />
			</Collapsable>

			<Collapsable bind:show={showForm}>
				<h1>{ Format(Language.auth_login_title) }</h1>
				<hr/>

				<div class="align-left">

					<TextInput class="w-100" type="text" label={Format(Language.auth_login_username)} />
					<TextInput class="w-100" type="password" label={Format(Language.auth_login_password)} />

					<Button class="w-100" on:click={handleLogin}>
						{Format(Language.auth_login_button_login)}
					</Button>

				</div>
			</Collapsable>

		</AuthContainer>
	</AuthScreen>
</main>
