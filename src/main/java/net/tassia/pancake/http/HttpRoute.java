package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;

public interface HttpRoute {
	byte[] route(Pancake pancake, HttpRequest request, String[] matches);
}
