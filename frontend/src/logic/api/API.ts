export class API {

	private readonly hostname: string = "http://localhost:8080";

	private async get(path: string, expectSuccess: boolean = true): Promise<Response> {
		const response = await fetch(this.hostname + path, {
			headers: {
			}
		});
		if (expectSuccess && response.status !== 200) {
			throw new Error(`Failed to fetch '${path}': Received ${response.status} ${response.statusText}`);
		}
		return response;
	}

	private async post(path: string, payload: any, expectSuccess: boolean = true): Promise<Response> {
		const response = await fetch(this.hostname + path, {
			method: "POST",
			body: JSON.stringify(payload),
			headers: {
			}
		});
		if (expectSuccess && response.status !== 200) {
			throw new Error(`Failed to fetch '${path}': Received ${response.status} ${response.statusText}`);
		}
		return response;
	}





	public async AuthLogin(username: string, password: string): Promise<void> {
		return new Promise(function(resolve, reject) {
			// TODO
			setTimeout(resolve, 2000);
		});
	}





	public async FetchCookiesRaw(expectSuccess: boolean = true): Promise<Response> {
		return await this.get("/api/v1/legal/cookies", expectSuccess);
	}

	public async FetchCookies(expectSuccess: boolean = true): Promise<string> {
		const response = await this.FetchCookiesRaw(expectSuccess);
		return await response.text();
	}

	public async FetchPrivacyRaw(expectSuccess: boolean = true): Promise<Response> {
		return await this.get("/api/v1/legal/privacy", expectSuccess);
	}

	public async FetchPrivacy(expectSuccess: boolean = true): Promise<string> {
		const response = await this.FetchPrivacyRaw(expectSuccess);
		return await response.text();
	}

	public async FetchTermsOfServiceRaw(expectSuccess: boolean = true): Promise<Response> {
		return await this.get("/api/v1/legal/tos", expectSuccess);
	}

	public async FetchTermsOfService(expectSuccess: boolean = true): Promise<string> {
		const response = await this.FetchTermsOfServiceRaw(expectSuccess);
		return await response.text();
	}

}
