package com.ar.RMI.model.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
	public Integer[] addVector(Integer[] v1, Integer[] v2) throws RemoteException;
	public Integer[] susVector(Integer[] v1, Integer[] v2) throws RemoteException;
}
