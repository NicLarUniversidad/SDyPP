package socket.server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.server.input.Keyboard;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyEchoServer {

    protected Logger log;
    protected int port;
    protected boolean running = true;
    protected ServerSocket server;
    public MyEchoServer(int port) throws IOException {
        this.port = port;
        this.log = LoggerFactory.getLogger(this.getClass());
        this.server = new ServerSocket(port);
    }

    public void run() throws IOException {
        log.info( "Creado servidor en el puerto " + port + "... Ingresar S y luego Enter para salir..." );
        Keyboard keyboard = new Keyboard();
        while(running){

            Socket client = server.accept();
            log.info("Se ha iniciado una conexión con " + client.getInetAddress() + ":" + client.getLocalPort());
            BufferedReader inputChannel = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter outputChannel = new PrintWriter(client.getOutputStream(), true);
            String output = "";
            outputChannel.println("Se ha conectado al servidor de echo. Ingrese lo que quiera y el servidor replicará su mensaje y se lo devolverá. Ingresar \"EXIT\" y pulse enter para salir ");
            while (! "EXIT".equals(output) && running){
                output = inputChannel.readLine();
                outputChannel.println(output);
                log.info("Se respondió al cliente: " + output);
                output = output.toUpperCase();
                if (keyboard.isKeyPressed('s') | keyboard.isKeyPressed('S')){
                    running = false;
                    client.close();
                }
            }
            client.close();
            outputChannel.println("Se ha desconectado...");
            log.info( client.getInetAddress() + ":" + client.getLocalPort() + "Se ha desconectado..." );
            if (keyboard.isKeyPressed('s') | keyboard.isKeyPressed('S')){
                running = false;
            }
        }
        log.info( "Se ha detenido el servidor de eco..." );
    }
}
