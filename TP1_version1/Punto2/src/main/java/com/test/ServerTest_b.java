package com.test;

import java.io.IOException;
import java.net.UnknownHostException;

import com.ar.model.server.Servidor;

/**
 * Hello world!
 *
 */
public class ServerTest_b 
{
    public static void main( String[] args )
    {
		Servidor sv = new Servidor();
    	try {
			sv.runServer();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
