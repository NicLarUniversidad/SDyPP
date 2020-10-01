package com.ar.RMI.model.services.alfaVer;

import java.rmi.RemoteException;

public class ConcretRemoteServiceChangVal extends ConcretRemoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConcretRemoteServiceChangVal() throws RemoteException {
		super();
	}

	@Override
	public Integer[] addVector(Integer[] v1, Integer[] v2) throws RemoteException {
		log.debug("Se cambiaron los vectores por nulos");
		return super.addVector(null, null);
	}

	@Override
	public Integer[] susVector(Integer[] v1, Integer[] v2) throws RemoteException {
		log.debug("Se cambiaron los vectores por nulos");
		return super.susVector(null, null);
	}

	
	
}
