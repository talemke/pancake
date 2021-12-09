<script lang="ts">
	let className: string = "";
	export { className as class };

	export let active: boolean = false;
	export let type: "default" | "primary" | "warning" | "danger" = "default";

	function generateClass(customClass: string, active: boolean, type: string): string {
		let className = "button button-" + type;
		if (active) className += " button-active";
		if (customClass) className += " " + customClass;
		return className;
	}
</script>

<button class={generateClass(className, active, type)} on:click>
	<slot />
</button>
