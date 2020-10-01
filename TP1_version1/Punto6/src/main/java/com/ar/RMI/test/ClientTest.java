package com.ar.RMI.test;

import com.ar.RMI.model.client.Client;

public class ClientTest {

	public static void main(String[] args) {
		Client c = new Client();
		c.run("localhost", 8006);
	}

}
