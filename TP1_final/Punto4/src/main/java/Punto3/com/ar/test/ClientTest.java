package Punto3.com.ar.test;

import java.io.IOException;
import java.net.UnknownHostException;

import Punto3.com.ar.client.model.Client;

public class ClientTest {

	public static void main(String[] args) {
		Client cl = new Client();
		try {
			cl.run();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
