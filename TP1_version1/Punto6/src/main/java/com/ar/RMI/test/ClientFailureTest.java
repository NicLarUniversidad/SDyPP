package com.ar.RMI.test;

import com.ar.RMI.model.client.ClientFailure;

public class ClientFailureTest {
	
	public static void main(String[] args) {
		ClientFailure c = new ClientFailure();
		c.run("localhost", 8006);
	}

}
