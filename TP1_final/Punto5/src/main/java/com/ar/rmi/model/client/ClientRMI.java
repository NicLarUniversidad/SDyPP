package com.ar.rmi.model.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.rmi.model.services.RemoteService;


public class ClientRMI extends Client {

	public ClientRMI(String add, int port) {
		super(add, port);
	}

	@Override
	public void subscribe() throws UnknownHostException, IOException, NotBoundException {
		Logger log = LoggerFactory.getLogger(this.getClass());
		RemoteService srv = (RemoteService) Naming.lookup("//" + add + ":" + port + "/Clima");
		log.debug("Se obtuvo un servicio de //" + add + ":" + port + "/Clima");
		while (true) {
			String server = srv.Suscribe();
			System.out.println(server);
			log.debug("Server: " + server);
		}
	}

	@Override
	public void listen() throws IOException {
		
	}

}
