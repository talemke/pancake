<script lang="ts">
	let className: string = "";
	export { className as class };

	export let label: string = "";
	export let hint: string = "";
	export let underline: string = "";
	export let clearable: boolean = false;
	export let disabled: boolean = false;
	export let type: string = "text";

	// TODO: Underline
	// TODO: Clearable
	// TODO: Disabled
	// TODO: Length
	// TODO: Error

	let input: HTMLInputElement;

	function handleInput() {
		if (input.value.trim() === "") {
			input.classList.add("empty");
		} else {
			input.classList.remove("empty");
		}
	}
</script>

<main class={className}>

	<input type={type} placeholder={hint} class="empty" on:input={handleInput} bind:this={input} />
	<label>{label}</label>

	<div class="underline" />
	<div class="underline-focus" />

</main>

<style lang="css">
	main {
		display: inline-block;
		position: relative;
		box-sizing: border-box;
		padding-top: 16px;
		min-height: 64px;
	}

	input {
		width: 100%;
		min-height: 32px;
		padding: 0;
		border: none;
		outline: none;
		background-color: transparent;
		font: inherit;
		caret-color: var(--secondary);
	}

	label {
		position: absolute;
		top: 23px;
		left: 0;
		transition: transform 0.15s ease-out;
		transform-origin: top left;
		pointer-events: none;
		color: var(--label);
		font-size: 13px;
		line-height: 18px;
	}

	input:focus ~ label, input:not(.empty) ~ label {
		color: var(--pancake-main-color);
		transform: translateY(-20px) scale(0.92308);
	}

	.underline {
		border-bottom: var(--border-light);
	}

	.underline-focus {
		width: 100%;
		height: 2px;
		transform: scaleX(0);
		transform-origin: top left;
		margin-top: -1px;
		background-color: var(--pancake-main-color);
	}

	input:focus ~ .underline-focus {
		transition: transform 0.15s ease-out;
		transform: scaleX(1);
	}
</style>
