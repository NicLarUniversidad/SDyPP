package com.ar.rmi.model.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread implements Runnable {
	
	private int port;

	public ClientThread(int port) {
		this.port = port;
	}

	public void run() {
		try {
			ServerSocket socket = new ServerSocket(port);
			Socket s = socket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			boolean running = true;
			while (running) {
				System.out.println("Servidor: " + in.readLine());
			}
			socket.close();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
