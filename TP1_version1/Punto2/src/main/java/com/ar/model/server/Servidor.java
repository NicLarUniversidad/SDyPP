package com.ar.model.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Servidor {
	
	private boolean run;
	private Logger log;
	
	public Servidor() {
		super();
		this.run = true;
		this.log = LoggerFactory.getLogger(this.getClass());
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

	public void runServer() throws UnknownHostException, IOException {
		ServerSocket serverSocket = new ServerSocket(8000);
		log.debug("Se ha levantado un servidor en el puerto 8000");
		while(run) {
			Thread t = new Thread(new ServerThread(serverSocket.accept()));
			t.start();
		}
		serverSocket.close();
	}

}
