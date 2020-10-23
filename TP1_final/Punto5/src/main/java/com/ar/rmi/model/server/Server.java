package com.ar.rmi.model.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	protected int port;
	public static boolean running = true;
	protected String city;
	protected String C;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public Server(int port) {
		this.port = port;
	}

	public void listenClients() throws IOException {
		AddClientsThread act = new AddClientsThread(new ServerSocket(this.port));
		Thread t = new Thread(act);
		t.start();
	}

	public void startRMI() {
		this.city = "Mercedes";
		this.C = "20";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true/*Server.running*/) {
			System.out.println("La temperatura de " + this.city 
					+ "es de " + this.C + "° Celcius\n"
					+ "Ingresar...\n"
					+ "1 - para modificar ciudad\n"
					+ "2 - para modificar temperatura");
			String op = sc.nextLine();
			switch(op.charAt(0)) {
			case '1':
				System.out.println("Ingresar nombre del nuevo lugar, que se a a medir su temperatura");
				this.city = sc.nextLine();
				RMI.notifyObservers(this.city, this.C);
				
				break;
			case '2':
				System.out.println("Ingresar nueva temperatura");
				this.C = sc.nextLine();
				RMI.notifyObservers(this.city, this.C);
				break;
			default:
				System.out.println("Opcion inválida");
			}
		}
	}
	
	public void notifyObservers(ArrayList<Socket> observadores) {
		ServerNotifyThread snt = new ServerNotifyThread(observadores, city, C);
		Thread t = new Thread(snt);
		t.start();
	}

}
