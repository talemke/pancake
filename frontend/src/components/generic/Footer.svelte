<script lang="ts">
	import { link } from "svelte-spa-router";
	import { Format, Language, Pancake } from "../../logic/Pancake";
import Link from "../text/Link.svelte";
</script>

<main>
	<div class="footer-wrapper">
		<div class="footer-container">

			<div class="child child-1">
				<Link href="/legal/privacy">{Format(Language.footer_privacy)}</Link>
				&ensp;-&ensp;
				<Link href="/legal/cookies">{Format(Language.footer_cookies)}</Link>
				&ensp;-&ensp;
				<Link href="/legal/tos">{Format(Language.footer_tos)}</Link>
			</div>

			<div class="child child-2">
				<Link href={Pancake.ProjectURL}>Pancake</Link>
				&ensp;-&ensp;
				<Link href={Pancake.ReleaseURL}><code>{Pancake.ReleaseName}</code></Link>
			</div>

			<div class="child child-3">
				<Link href={Pancake.FeedbackURL}>{Format(Language.footer_feedback)}</Link>
			</div>

		</div>
	</div>
</main>

<style lang="css">
	.footer-wrapper {
		margin: var(--lg1) var(--lg3) 0 var(--lg3);
		padding-top: var(--md1);
		border-top: var(--border-light);
	}

	.footer-container {
		display: flex;
		justify-content: space-between;
	}

	.footer-container .child {
		flex-grow: 1;
		color: var(--text);
	}

	.footer-container .child-1 {
		text-align: left;
	}

	.footer-container .child-2 {
		text-align: center;
	}

	.footer-container .child-3 {
		text-align: right;
	}
</style>
