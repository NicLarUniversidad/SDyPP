package Punto3.com.ar.client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.model.utils.chronometer.versionAlfa.Chronometer;

import Punto3.com.ar.client.model.message.Parser;

public class Client {
	
	protected Logger logger;
	
	public Client () {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public void run() throws UnknownHostException, IOException {
		Socket c = new Socket("localhost", 7000);
		BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		PrintWriter out = new PrintWriter(c.getOutputStream(), true);
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		Chronometer chr = new Chronometer();
		while (running) {
			chr.start();
			String r = in.readLine();
			System.out.println("Servidor: " + Parser.parse(r));
			chr.stop();
			logger.debug("Servidor: " + r);
			logger.info("Servidor: [" + r + "] en " + chr.getTime() + " milisegundos");
			Request rq = new Request(running, sc);
			running = rq.isRunning();
			r = (rq).toString();
			logger.debug("Cliente: " + r );
			out.println(r);
		}
		c.close();
		logger.debug("Se ha cerrado la conexión con el servidor");
		sc.close();
	}
}
