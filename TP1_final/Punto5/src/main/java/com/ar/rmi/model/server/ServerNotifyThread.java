package com.ar.rmi.model.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerNotifyThread implements Runnable {
	
	private ArrayList<Socket> obs;
	private String city;
	private String C;

	public ServerNotifyThread(ArrayList<Socket> observadores, String city, String c2) {
		this.obs = observadores;
		this.city = city;
		this.C = c2;
	}

	public void run() {
		try {
			for (int i = 0 ; i < obs.size() ; i++) {
				PrintWriter out = new PrintWriter(obs.get(i).getOutputStream(), true);
				out.println("La temperatura en " + city
							+ " es de " + C
							+ " grados celcius");
			}
		}
		catch(Exception e) {
			
		}
	}

}
