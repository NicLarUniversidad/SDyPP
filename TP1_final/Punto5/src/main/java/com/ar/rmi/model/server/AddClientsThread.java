package com.ar.rmi.model.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AddClientsThread implements Runnable {
	
	private ServerSocket serverSocket;

	public AddClientsThread(ServerSocket serverSocket) throws IOException {
		this.serverSocket = serverSocket;
	}

	public void run() {
		while (true) {
			try {
				Socket c = this.serverSocket.accept();
				RMI.addClient(c);
			} catch (IOException e) {
				
			}
		}
	}

}
