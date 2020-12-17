package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServicioRemoto extends Remote {
        public String descargar(String path) throws RemoteException;
}
