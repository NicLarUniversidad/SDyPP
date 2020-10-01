package com.ar.RMI.model.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class STR_UPPERCASE_Service extends UnicastRemoteObject implements RemoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String str;
	private Logger log;
	
	public STR_UPPERCASE_Service() throws RemoteException {
		super();
		this.setLog(LoggerFactory.getLogger(this.getClass()));
	}


	public String execute() throws RemoteException {
		if (str==null) {
			log.warn("Se intentó ejectutar la operacion con el string nulo");
			return null;
		}
		else {
			log.debug("Se ejecutó la operacion, se devolvio: " + str.toUpperCase());
			return str.toUpperCase();
		}
	}

	public void setStr(String str) {
		this.str = str;
	}

	public boolean loadParams(String param) throws RemoteException {
		try {
			this.str = param;
			log.debug("Se setteo el string: " + str);
			return true;
		}
		catch (Exception e) {
			log.error("Fallo al cargar parametro");
			return false;
		}
	}


	public void setLog(Logger log) {
		this.log = log;
	}

}
