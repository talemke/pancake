package net.tassia.pancake;

public class PancakeLauncher {

	public static void main(String[] args) {
		try {
			new Pancake().start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
