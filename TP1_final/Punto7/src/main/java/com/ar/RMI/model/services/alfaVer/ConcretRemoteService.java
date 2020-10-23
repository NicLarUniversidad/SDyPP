package com.ar.RMI.model.services.alfaVer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ar.RMI.model.services.RemoteService;

public class ConcretRemoteService extends UnicastRemoteObject implements RemoteService {

	private static final long serialVersionUID = 1L;

	public ConcretRemoteService () throws RemoteException {
		
	}

	public Integer[] addVector(Integer[] v1, Integer[] v2) throws RemoteException {
		Integer size = Integer.max(v1.length, v2.length);
		Integer[] res = new Integer[size];
		for (int i = 0 ; i<size ; i++)
			res[i] = v1[i] + v2[i];
		return res;
	}
	
	public Integer[] susVector(Integer[] v1, Integer[] v2) throws RemoteException {
		Integer size = Integer.max(v1.length, v2.length);
		Integer[] res = new Integer[size];
		for (int i = 0 ; i<size ; i++)
			res[i] = v1[i] - v2[i];
		return res;
	}

	public String execute() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadParams() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public boolean loadParams(String param) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
