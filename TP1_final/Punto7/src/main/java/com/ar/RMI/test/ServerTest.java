package com.ar.RMI.test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.RMI.model.services.Constants;
import com.ar.RMI.model.services.DigitsOfPI;
import com.ar.RMI.model.services.RemoteService;
import com.ar.RMI.model.services.ReverseService;
import com.ar.RMI.model.services.STR_UPPERCASE_Service;
import com.ar.RMI.model.services.alfaVer.ConcretRemoteService;

/**
 * Hello world!
 *
 */
public class ServerTest 
{
    public static void main( String[] args )
    {
    	try {
    		Logger log = LoggerFactory.getLogger(Class.forName("com.ar.RMI.test.ServerTest"));
    		RemoteService[]  svc = new RemoteService[3]; 
            LocateRegistry.createRegistry(8006); 
            log.debug("Se abrio un RMI Registry en el puerto 8006");
            Naming.rebind("rmi://localhost:8006/Vector", svc[0] = new ConcretRemoteService());
            log.debug("URL: rmi://localhost:8006/Vector up");
            Naming.rebind("rmi://localhost:8006/" + Constants.path[0], svc[1] = new ReverseService());
            log.debug("URL: rmi://localhost:8006/" + Constants.path[0] + "up");
            Naming.rebind("rmi://localhost:8006/" + Constants.path[1], svc[2] = new STR_UPPERCASE_Service());
            log.debug("URL: rmi://localhost:8006/" + Constants.path[1] + " up");
            Naming.rebind("rmi://localhost:8006/" + Constants.path[2], svc[2] = new DigitsOfPI());
            log.debug("URL: rmi://localhost:8006/" + Constants.path[2] + " up");
    	} catch(Exception e) {
    		
    	}
    }
}
