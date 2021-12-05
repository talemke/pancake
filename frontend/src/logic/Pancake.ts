export { en_us as Language } from "./language/en-us";

export function Format(str: string): string {
	return str.replace("{brand_name}", "Pancake");
}
