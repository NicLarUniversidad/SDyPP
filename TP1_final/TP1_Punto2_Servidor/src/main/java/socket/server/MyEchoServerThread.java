package socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.server.input.Keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyEchoServerThread implements Runnable {
    protected Logger log;
    protected Socket client;
    public MyEchoServerThread(Socket client) {
        this.client = client;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public void run() {
        try {
            log.info("Se ha iniciado una conexión con " + client.getInetAddress() + ":" + client.getPort());
            BufferedReader inputChannel = null;
                inputChannel = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter outputChannel = new PrintWriter(client.getOutputStream(), true);
            String output = " ";
            boolean running = true;
            outputChannel.println("Se ha conectado al servidor de echo. Ingrese lo que quiera y el servidor replicará su mensaje y se lo devolverá. Ingresar \"EXIT\" y pulse enter para salir ");
            while ((! "EXIT".equals(output) ) && running){
                try {
                    output = inputChannel.readLine();
                } catch (IOException e) {
                    log.error(e.getLocalizedMessage());
                    running = false;
                }
                outputChannel.println(output);
                log.info("Se respondió al cliente "+ client.getInetAddress() + ":" + client.getPort() + ": " + output);
                output = output.toUpperCase();
            }
            client.close();
            outputChannel.println("Se ha desconectado...");
            log.info( client.getInetAddress() + ":" + client.getLocalPort() + "Se ha desconectado..." );
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
