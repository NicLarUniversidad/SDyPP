package com.ar.RMI.model.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
	public String execute() throws RemoteException;
	public boolean loadParams(String param) throws RemoteException;
}
