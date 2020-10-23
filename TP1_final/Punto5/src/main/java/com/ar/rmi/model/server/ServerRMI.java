package com.ar.rmi.model.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.rmi.model.server.Server;
import com.ar.rmi.model.services.RemoteService;
import com.ar.rmi.model.services.concrete.TempService;

public class ServerRMI extends Server {

	@Override
	public void listenClients() throws IOException {
		
	}

	@Override
	public void notifyObservers(ArrayList<Socket> observadores) {
		
	}

	public ServerRMI(int port) {
		super(port);
	}

	@Override
	public void startRMI() {
		Logger log = LoggerFactory.getLogger(this.getClass());
        try {
			TempService.init();
        	RemoteService srv = new TempService();
			LocateRegistry.createRegistry(this.port);
			log.debug("Creado registro en el puerto " + this.port);
			Naming.rebind("rmi://localhost:" + this.port + "/Clima", srv);
			log.debug("Creado servicio en //localhost:" + this.port + "/Clima");
			TempService.init();
			Scanner sc = new Scanner(System.in);
			while (true/*Server.running*/) {
				System.out.println("La temperatura de " + TempService.ciudad 
						+ " es de " + TempService.temperatura + "° Celcius\n");
				TempService.cambiar(sc);
			}
		} catch (RemoteException e) {
			log.error("Error de conexion");
		} catch (MalformedURLException e) {
			log.error("URL mal formada");
		} 
	}

}
