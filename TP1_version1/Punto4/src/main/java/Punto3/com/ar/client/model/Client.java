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
		while (running) {
			String r = in.readLine();
			logger.info("Servidor: " + r);
			System.out.println("Servidor: " + Parser.parse(r));
			Request rq = new Request(running, sc);
			running = rq.isRunning();
			r = (rq).toString();
			logger.info("Cliente: " + r );
			out.println(r);
		}
		c.close();
		sc.close();
	}
}
