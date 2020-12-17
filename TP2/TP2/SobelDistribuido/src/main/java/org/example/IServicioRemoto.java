package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServicioRemoto extends Remote {
    public Imagen getImagen(Imagen img) throws RemoteException;
}
