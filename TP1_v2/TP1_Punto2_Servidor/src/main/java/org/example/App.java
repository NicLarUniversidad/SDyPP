package org.example;

import socket.server.MyEchoServer;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyEchoServer server = null;
        try {
            server = new MyEchoServer(12000);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
