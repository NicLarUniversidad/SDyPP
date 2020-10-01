package com.ar.RMI.model.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.RMI.model.services.Constants;
import com.ar.RMI.model.services.RemoteService;

public class ServiceFactory {
	
	public static Scanner sc = new Scanner(System.in);
	private static Logger log;
	
	public static void init() {
		try {
			log = LoggerFactory.getLogger(Class.forName("com.ar.RMI.model.client.ServiceFactory"));
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	public static RemoteService createService (int key, String add, int port) throws RemoteException, MalformedURLException, NotBoundException {
		String path;
		RemoteService salida;
		try {
			path = "//" + add + ":" + port + "/" + Constants.path[key];
			salida = (RemoteService) Naming.lookup("//" + add + ":" + port + "/" + Constants.path[key]);
		}
		catch (Exception e) {
			path = "//" + add + ":" + port + "/Vector";
			salida = (RemoteService) Naming.lookup("//" + add + ":" + port + "/Vector");
			log.warn("No se ha encontrado una URL para los parametros key: " + key + ", addr: " + add +", port:  " + port);
		}
		log.debug("Se devolvio la URL " + path + " con los parametros key: " + key + ", addr: " + add +", port:  " + port);
		return salida;
	}

}
