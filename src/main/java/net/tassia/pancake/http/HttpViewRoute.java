package net.tassia.pancake.http;

public abstract class HttpViewRoute implements HttpRoute {
	private final HttpView view;

	public HttpViewRoute(String view) {
		this.view = new HttpView(view);
	}

	public HttpViewRoute(byte[] view) {
		this.view = new HttpView(view);
	}

	protected byte[] view(String[]...replacements) {
		return view.viewData(replacements);
	}

}
