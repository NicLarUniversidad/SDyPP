package org.example;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class App
{
    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static void main( String[] args ) throws IOException, TimeoutException, NotBoundException {
        if (args.length < 3){
            System.out.println("Ingresar parámetros: <ip> <puerto> <rabbit ip>");

        } else {
            Peer peer = new Peer(args[0], args[1], args[2]);
            Boolean continuar = true;
            Scanner scanner = new Scanner(System.in);
            while (continuar) {
                System.out.println("Ingresar una opción");
                System.out.println("1 - Subir un archivo");
                System.out.println("2 - Ver lista de archivos");
                System.out.println("3 - Salir");
                String op = scanner.nextLine();
                switch (op.charAt(0)) {
                    case '1':
                        System.out.println("Ingrese la ruta del archivo a subir");
                        String path = scanner.nextLine();
                        System.out.println(peer.subirArchivo(path));
                        break;
                    case '2':
                        Boolean descargar = true;
                        while (descargar) {
                            System.out.println("Ingresar un índice para decargar archivo");
                            System.out.println("V - Volver");
                            int c = 0;
                            List<String> archivos = peer.recuperarArchivos();
                            for (String mensaje : archivos){
                                System.out.println(c + " - " + mensaje);
                            }
                            op = scanner.nextLine();
                            if (op.charAt(0) == 'V' || op.charAt(0) == 'v') {
                                descargar = false;
                            } else {
                                if (isNumeric(op)) {
                                    peer.descargar(archivos.get(Integer.parseInt(op)));
                                    descargar = false;
                                    System.out.println("Archivo descargado, volviendo al menú anterior");
                                } else {
                                    System.out.println("Índice ingresado no numérico");
                                }
                            }
                        }
                        break;
                    case '3':
                            continuar = false;
                            System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción ingresada inválida");
                }
            }
        }
    }
}
