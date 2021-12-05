<script lang="ts">
	import LoadingSpinner from "../../components/LoadingSpinner.svelte";
import { Format, Language } from "../../logic/Pancake";
</script>

<main>
	<div class="full-screen center auth-screen">
		<div class="auth-container">

			<LoadingSpinner />

			<h1>{ Format(Language.auth_login_title) }</h1>
			<hr/>

			<div class="align-left">

				<h6 class="mt-4">{Format(Language.auth_login_username)}:</h6>
				<input type="text" placeholder={Format(Language.auth_login_username)} class="form-control form-control-lg" />

				<h6 class="mt-4">{Format(Language.auth_login_password)}:</h6>
				<input type="password" placeholder={Format(Language.auth_login_password)} class="form-control form-control-lg" />

				<button class="btn btn-lg btn-primary w-100 mt-5">{Format(Language.auth_login_button_login)}</button>

			</div>

		</div>
	</div>
</main>
