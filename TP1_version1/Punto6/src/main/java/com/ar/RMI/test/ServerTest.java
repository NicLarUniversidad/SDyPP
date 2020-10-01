package com.ar.RMI.test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.ar.RMI.model.services.alfaVer.ConcretRemoteService;
import com.ar.RMI.model.services.alfaVer.ConcretRemoteServiceChangVal;

/**
 * Hello world!
 *
 */
public class ServerTest 
{
    public static void main( String[] args )
    {
    	try {
            ConcretRemoteService srv = new ConcretRemoteService();
            LocateRegistry.createRegistry(8006); 
            Naming.rebind("rmi://localhost:8006/Vector", srv);
            Naming.rebind("rmi://localhost:8006/VectorF", new ConcretRemoteServiceChangVal());
    	} catch(Exception e) {
    		
    	}
    }
}
