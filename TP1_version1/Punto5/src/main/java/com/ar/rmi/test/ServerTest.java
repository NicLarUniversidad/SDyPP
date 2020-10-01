package com.ar.rmi.test;

import com.ar.rmi.model.server.Server;
import com.ar.rmi.model.server.ServerRMI;

public class ServerTest {
	public static void main(String args[]) {
		try {
			Server sv = new ServerRMI(8004);
			sv.listenClients();
			sv.startRMI();
		}
		catch(Exception e) {
			
		}
	}
}
