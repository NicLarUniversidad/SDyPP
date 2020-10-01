package com.ar.rmi.model.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class RMI {
	
	private static ArrayList<Socket> observadores = new ArrayList<Socket>();

	public static void addClient(Socket c) throws IOException {
		observadores.add(c);
		PrintWriter out = new PrintWriter(c.getOutputStream(), true);
		out.println("Se ha agregado a " + c.getInetAddress() + ":" + c.getPort());
	}

	public static void notifyObservers(String city, String c) {
		@SuppressWarnings("unchecked")
		ServerNotifyThread snt = new ServerNotifyThread((ArrayList<Socket>)observadores.clone(), city, c);
		Thread t = new Thread(snt);
		t.start();
	}

}
