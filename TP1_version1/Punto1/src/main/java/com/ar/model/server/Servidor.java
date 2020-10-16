package com.ar.model.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.model.utils.IChronometer;
import com.ar.model.utils.UtilsFactory;

public class Servidor {
	
	private boolean run;
	private Logger log;
	private IChronometer chr;
	
	public Servidor() {
		super();
		this.run = true;
		this.log = LoggerFactory.getLogger(this.getClass());
		this.chr = UtilsFactory.createChronometer();
	}

	protected boolean isRun() {
		return run;
	}

	protected void setRun(boolean run) {
		this.run = run;
	}

	protected Logger getLog() {
		return log;
	}

	protected void setLog(Logger log) {
		this.log = log;
	}

	protected IChronometer getChr() {
		return chr;
	}

	protected void setChr(IChronometer chr) {
		this.chr = chr;
	}

	public void runServer(int port) throws UnknownHostException, IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		log.debug("Se ha levantado un servidor en el puerto " + port);
		while(run) {
			Socket clientSocket = serverSocket.accept();
			log.debug("Se ha aceptado una conexión con un cliente, su IP es " + clientSocket.getInetAddress().toString()
					+ ", puerto: " + clientSocket.getPort());
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			String input = " ";
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
		}
		serverSocket.close();
		log.debug("Se ha cerrado el servidor");
	}
}
