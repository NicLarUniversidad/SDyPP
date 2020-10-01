package com.ar.model.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ar.model.utils.IChronometer;
import com.ar.model.utils.UtilsFactory;

public class ServerThread implements Runnable {
	
	protected Socket clientSocket;
	private Logger log;
	private IChronometer chr;
	private String add;
	
	public ServerThread( Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.add = clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort();
		this.log = LoggerFactory.getLogger(this.getClass());
		this.chr = UtilsFactory.createChronometer();
	}

	public ServerThread(InetAddress inetAddress, int port) throws IOException {
		this.clientSocket = new Socket(inetAddress, port);
		this.log = LoggerFactory.getLogger(this.getClass());
		this.chr = UtilsFactory.createChronometer();
	}

	public void run() {
		try {
			log.info("Se ha aceptado una conexión con un cliente, su IP es " + clientSocket.getInetAddress().toString()
					+ ", puerto: " + clientSocket.getPort(),"Servidor","runServer");
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			String input = " ";
			boolean run = true;
			while (run) {
				chr.reset();
				chr.start();
				input = in.readLine();
				chr.stop();
				if ((input==null)||input.equals("")) {
					run = false;
					log.debug("Se ha recibido una cadena vacia, cerrando el servidor");
				}
				else {
					log.debug("Se ha recibido un mensaje:[ " + input +"] en " + chr.getTime());
					out.println(input);
				}
			}
			clientSocket.close();
			log.debug("Se ha cerrado la conexion con " + add);
		}catch (Exception e) {
			log.error("Error con la conexion");
		}
	}

}
