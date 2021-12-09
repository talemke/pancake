export function Format(str: string): string {
	return str.replace("{brand_name}", "Pancake");
}

export function HandleError(reason: any) {
	// TODO
	console.error(reason);
}
