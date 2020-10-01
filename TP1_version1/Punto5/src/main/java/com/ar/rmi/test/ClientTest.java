package com.ar.rmi.test;

import com.ar.rmi.model.client.Client;
import com.ar.rmi.model.client.ClientRMI;

public class ClientTest {
	
	public static void main (String args[]) {
		try {
			Client c = new ClientRMI("localhost", 8004);
			c.subscribe();
		}
		catch(Exception e) {
			
		}
	}
}
