package org.example;

import com.google.gson.Gson;
import org.handler.RabbitMQHandler;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Peer{

    private String ip;
    private String puerto;
    private String rabbitIP;
    private Remote remote;
    private IServicioRemoto servicioRemoto;

    public Peer(String ip, String puerto, String rabbitIP) throws IOException, TimeoutException {
        this.ip = ip;
        this.puerto = puerto;
        this.rabbitIP = rabbitIP;
        RabbitMQHandler.init();

        servicioRemoto = new Archivo();
        remote = UnicastRemoteObject.exportObject(servicioRemoto,
                Integer.parseInt(puerto));
        Registry registry = LocateRegistry.createRegistry(Integer.parseInt(puerto));
        registry.rebind("archivo", remote);
    }

    public String subirArchivo(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return "Archivo no existe";
        }
        Archivo archivo = new Archivo(ip, puerto, path);
        RabbitMQHandler.publicarMensaje(archivo, "colaCompartidos");
        return "Se subió el archivo";
    }

    public List<String> recuperarArchivos() throws IOException {
        return RabbitMQHandler.leerTodosLosMensajes("colaCompartidos");
    }

    public void descargar(String String) throws IOException, NotBoundException {
        Gson gson = new Gson();
        Archivo archivo = gson.fromJson(String, Archivo.class);
        archivo.descargar();
    }
}