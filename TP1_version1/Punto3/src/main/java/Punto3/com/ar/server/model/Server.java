package Punto3.com.ar.server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Punto3.com.ar.server.model.message.MessageManager;

public class Server {
	
	private boolean running = true;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void run() throws IOException {
		MessageManager.init();
		ServerSocket ss = new ServerSocket(7000);
		logger.debug("Se ha iniciado el servidor, en la direccion: " + ss.getLocalSocketAddress() + ", puerto: "+ ss.getLocalPort());
		while (this.running) {
			Socket client = ss.accept();
			logger.debug("Se ha aceptado a un cliente: IP: " + client.getInetAddress() +", puerto: " + client.getPort());
			Thread t = new Thread(new ServerThreadEnhanced(client));
			t.start();
		}
		ss.close();
	}
	
}
