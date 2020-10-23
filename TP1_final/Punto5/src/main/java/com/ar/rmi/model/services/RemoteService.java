package com.ar.rmi.model.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
	public String Suscribe() throws RemoteException;
}
