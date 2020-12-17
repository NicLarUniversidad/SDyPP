package org.example;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cliente {

    Scanner sc = new Scanner (System.in);
    static Logger log = LoggerFactory.getLogger(Cliente.class);


    public static void main(String[] args) throws NotBoundException, IOException {
        Registry clienteRMI = LocateRegistry.getRegistry("127.0.0.1",13000);
        IServicioRemoto cliStub = (IServicioRemoto) clienteRMI.lookup("Tarea");
        File file = null;
        file = new File("prueba.jpg");
        Imagen imagen = new Imagen(ImageIO.read(file));
        log.info("Enviando la imagen: " + file.getName());
        Imagen returned = cliStub.getImagen(imagen);
        returned.persistImg("resultado.jpg");
    }
}