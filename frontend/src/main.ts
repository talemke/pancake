// Import the main application component
import Application from "./Application.svelte";

// Create the application component and render it to the documents body
const app = new Application({
	target: document.body,
});

// Export
export default app;
