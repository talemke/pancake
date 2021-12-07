export class API {

	public async AuthLogin(username: string, password: string): Promise<void> {
		return new Promise(function(resolve, reject) {
			// TODO
			setTimeout(resolve, 2000);
		});
	}

}
