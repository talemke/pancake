export type PancakeInformation = {
	ProjectName: string,
	ProjectURL: string,
	ReleaseName: string,
	ReleaseURL: string,
	FeedbackURL: string,
};

export type PopupManager = {
	ShowErrorPopup: (message: any) => void,
};
