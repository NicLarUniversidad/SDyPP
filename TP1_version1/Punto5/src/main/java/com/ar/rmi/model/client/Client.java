package com.ar.rmi.model.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;

public class Client {
	
	protected String add;
	protected int port;
	private Socket socket;

	public Client(String add, int port) {
		this.add = add;
		this.port = port;
	}

	public void subscribe() throws UnknownHostException, IOException, NotBoundException {
		this.socket = new Socket(add, port);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String x = in.readLine();
		System.out.println("Servidor: " + x);
	}

	public void listen() throws IOException {
		/*ClientThread ct = new ClientThread(8004);
		Thread t = new Thread(ct);
		t.start();*/
		while (true) {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String x = in.readLine();
			System.out.println("Servidor: " + x);
		}
	}

}
