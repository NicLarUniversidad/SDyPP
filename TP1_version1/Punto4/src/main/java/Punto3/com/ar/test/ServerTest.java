package Punto3.com.ar.test;

import java.io.IOException;

import Punto3.com.ar.server.model.Server;

public class ServerTest {
	
	public static void main(String args[]) {
		Server sv = new Server();
		try {
			sv.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
