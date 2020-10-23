package com.ar.RMI.model.services.alfaVer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.RMI.model.services.RemoteService;

public class ConcretRemoteService extends UnicastRemoteObject implements RemoteService {

	private static final long serialVersionUID = 1L;
	
	protected Logger log;

	public ConcretRemoteService () throws RemoteException {
		this.log = LoggerFactory.getLogger(this.getClass());
	}

	public Integer[] addVector(Integer[] v1, Integer[] v2) throws RemoteException {
		if ((v1==null) || (v2==null))
			return null;
		Integer size = Integer.min(v1.length, v2.length);
		Integer[] res = new Integer[size];
		for (int i = 0 ; i<size ; i++)
			res[i] = v1[i] + v2[i];
		return res;
	}
	
	public Integer[] susVector(Integer[] v1, Integer[] v2) throws RemoteException {
		if ((v1==null) || (v2==null))
			return null;
		Integer size = Integer.max(v1.length, v2.length);
		Integer[] res = new Integer[size];
		for (int i = 0 ; i<size ; i++)
			res[i] = v1[i] - v2[i];
		return res;
	}
	
	protected void notificar(Integer[] v1, Integer[] v2) {
		String o = "Vectores: v1:{";
		if (v1 == null)
			o+="null";
		else
			for (int i = 0 ; i < v1.length ; i++ )
				o+=v1[i]+", ";
		o+=", v2 :{";
		if (v2 == null)
			o+="null";
		else
			for (int i = 0 ; i < v2.length ; i++ )
				o+=v2[i]+", ";
		o+="}";
		System.out.println(o);
		log.debug(o);
	}

}
