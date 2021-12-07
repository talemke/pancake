// Functions & types
export * from "./Helpers";
export * from "./Types";


// Language
export { en_us as Language } from "./language/en-us";


// API
import { API as ApiClass } from "./API";
const ApiInstance = new ApiClass();
export function API(): ApiClass {
	return ApiInstance;
}


// Utility class
export class Pancake {

	public static readonly ProjectName = "Pancake";
	public static readonly ProjectURL = "https://github.com/TASSIA710/pancake";
	public static readonly ReleaseName = "v0.0.1-PRE.1";
	public static readonly ReleaseURL = "https://github.com/TASSIA710/pancake/releases";
	public static readonly FeedbackURL = "https://github.com/TASSIA710/pancake/issues";

}
