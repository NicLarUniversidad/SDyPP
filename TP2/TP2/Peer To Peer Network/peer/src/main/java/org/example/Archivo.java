package org.example;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Archivo implements IServicioRemoto {

    private String ip;
    private String puerto;
    private String path;

    public Archivo(){}

    public Archivo(String ip, String puerto, String path) {
        this.ip = ip;
        this.puerto = puerto;
        this.path = path;
    }

    public String getIP() {return ip;}
    public String getPuerto() {return puerto;}
    public String getPath() {return path;}

    public void descargar() throws IOException, NotBoundException {
        Registry clienteRMI = LocateRegistry.getRegistry(this.ip,Integer.parseInt(this.puerto));
        IServicioRemoto archivo = (IServicioRemoto) clienteRMI.lookup("archivo");
        String contenido = archivo.descargar(this.path);
        File file = new File("decargas_" + this.getPath());
        BufferedWriter br = new BufferedWriter(new FileWriter(file));
        br.write(contenido);
        br.flush();
        br.close();
    }

    @Override
    public String descargar(String path) throws RemoteException {
        File file = new File(path);
        String res = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                res += line +" \n";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}