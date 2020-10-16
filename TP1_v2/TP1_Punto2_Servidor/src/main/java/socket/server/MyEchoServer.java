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
            MyEchoServerThread runnable = new MyEchoServerThread(client);
            Thread thread = new Thread(runnable);
            thread.start();
            if (keyboard.isKeyPressed('s') | keyboard.isKeyPressed('S')){
                running = false;
            }
        }
        log.info( "Se ha detenido el servidor de eco..." );
    }
}
