import type {PancakeInformation, PopupManager} from "./Types";

export * from "./Helpers";
export * from "./Types";

export { config as Config } from "./config/config";
export { en_us as Language } from "./language/en-us";

import { API as ApiClass } from "./api/API";
export const API: ApiClass = new ApiClass();

export const Pancake: PancakeInformation = {
	ProjectName: "Pancake",
	ProjectURL: "https://github.com/TASSIA710/pancake",
	ReleaseName: "v0.0.1-PRE.1",
	ReleaseURL: "https://github.com/TASSIA710/pancake/releases",
	FeedbackURL: "https://github.com/TASSIA710/pancake/issues",
};

export const Popups: PopupManager = {

	ShowErrorPopup: (message) => {
		alert(message);
	},

};
