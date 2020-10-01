package com.ar.RMI.model.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DigitsOfPI extends UnicastRemoteObject implements RemoteService {

	public DigitsOfPI() throws RemoteException {
		super();
		this.digits = 0;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int digits;

	public String execute() throws RemoteException {
		String o = String.valueOf(Math.PI);
		int size = 2 + +this.digits;
		if (size>o.length())
			size = o.length();
		o = o.substring(0, size);
		return o;
	}

	public boolean loadParams(String param) throws RemoteException {
		try {
			this.digits = Integer.valueOf(param);
			if (this.digits<0) {
				this.digits = 0;
				return false;
			}
			else
			return true;
		}
		catch (Exception e) {
			this.digits = 0;
			return false;
		}
	}

}
