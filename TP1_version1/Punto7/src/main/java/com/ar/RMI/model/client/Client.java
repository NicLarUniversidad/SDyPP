package com.ar.RMI.model.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.RMI.model.services.RemoteService;
import com.ar.model.utils.chronometer.versionAlfa.Chronometer;

public class Client {
	
	private Logger logger;
	
	public Client() {
		this.setLogger(LoggerFactory.getLogger(this.getClass()));
	}

	public void run(String add, int port) {
		try {
			ServiceFactory.init();
			Scanner sc = new Scanner(System.in);
            boolean running = true;
            Chronometer chr = new Chronometer();
            while(running) {
            	System.out.println("Seleccionar una operacion\n"
            			+ "0 - Reverse Service\n"
            			+ "1 - UpperCase Service\n"
            			+ "2 - DigitsOfPI");
            	String op = sc.nextLine();
            	logger.debug("Usuario ingresó: " + op);
            	RemoteService svc = ServiceFactory.createService(Integer.valueOf(op), add, port);
            	System.out.println("Ingresar un parámetro");
            	op = sc.nextLine();
            	logger.debug("Al pedir un parámetro. Usuario ingresó: " + op);
            	if (svc.loadParams(op)) {
            		chr.start();
            		String svResp = svc.execute();
            		chr.stop();
            		System.out.println("Servidor: " + svResp);
            		logger.info("Servidor respondio: " + svResp + " en " + chr.getTime() + " milisegundos");
            	}
            	else
            		System.out.println("Parametro invalido");
            }
            sc.close();
        } catch(RemoteException e) {
        	logger.error("Remote Exception, hay problemas de conexión con el servidor.");
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException, URL de servicio inválida.");
		} catch (NotBoundException e) {
			logger.error("NotBoundException, investigar que es esto...");
		}
		
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
}
