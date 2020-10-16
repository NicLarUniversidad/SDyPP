package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.model.utils.IChronometer;
import com.ar.model.utils.UtilsFactory;

public class Cliente {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Cliente.class);
		IChronometer chr = UtilsFactory.createChronometer();
		try {
			Socket clientSocket = new Socket("localhost", 12000);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			Scanner sc = new Scanner(System.in);
			String salida = " ";
			boolean running = true;
			while (running) {
				System.out.println("Ingresar un mensaje para el servidor, pulsar sólo \"Enter\" para salir");
				salida = sc.nextLine();
				if (salida.equals("")) {
					running = false;
					System.out.println("Se ha salido del servicio de echo");
					log.debug("Se ha salido del servicio de echo");
				}
				else {
					log.debug("Intentando enviar un mensaje ["+salida+"] a localhost:8001");
					out.println(salida);
					chr.reset();
					chr.start();
					System.out.println(in.readLine());
					chr.stop();
					log.debug("Se ha recibido el echo en " + chr.getTime());
				}
			}
			clientSocket.close();
			log.debug("Se ha cerrado la conexión con el servidor");
			sc.close();
		} catch (UnknownHostException e) {
			log.error("No se ha encontrado el host solicitado: localhost:8000");
		} catch (IOException e) {
			log.error("Hubo un error I/O");
		}
		
	}

}
