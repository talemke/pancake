export class API {

	private readonly hostname: string = "http://localhost:8080";

	private async get(path: string): Promise<Response> {
		return await fetch(this.hostname + path, {
			headers: {
			}
		});
	}

	private async post(path: string, payload: any): Promise<Response> {
		return await fetch(this.hostname + path, {
			method: "POST",
			body: JSON.stringify(payload),
			headers: {
			}
		});
	}





	public async AuthLogin(username: string, password: string): Promise<void> {
		return new Promise(function(resolve, reject) {
			// TODO
			setTimeout(resolve, 2000);
		});
	}





	public async FetchTermsOfServiceRaw(): Promise<Response> {
		return await this.get("/api/v1/legal/tos2");
	}

	public async FetchTermsOfService(): Promise<string> {
		const response = await this.FetchTermsOfServiceRaw();
		// TODO: Non-200?
		console.log("test");
		return await response.text();
	}

}
