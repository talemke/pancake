export * from "./Helpers";
export * from "./Types";

export { config as Config } from "./config/config";
export { en_us as Language } from "./language/en-us";

import { API as ApiClass } from "./api/API";
export const API: ApiClass = new ApiClass();

export class Pancake {

	public static readonly ProjectName = "Pancake";
	public static readonly ProjectURL = "https://github.com/TASSIA710/pancake";
	public static readonly ReleaseName = "v0.0.1-PRE.1";
	public static readonly ReleaseURL = "https://github.com/TASSIA710/pancake/releases";
	public static readonly FeedbackURL = "https://github.com/TASSIA710/pancake/issues";

}
