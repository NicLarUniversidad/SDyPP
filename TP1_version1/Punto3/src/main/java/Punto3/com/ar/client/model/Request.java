package Punto3.com.ar.client.model;

import java.util.Scanner;

public class Request {
	
	private String request;
	private boolean running;

	public Request(boolean running, Scanner sc) {
		System.out.println("Ingresar una opción\n"
				+ "1 - Mandar un mensaje\n"
				+ "2 - Leer mensajes\n"
				+ "3 - Salir");
		String op = sc.nextLine();
		this.running = running;
		switch(op.charAt(0)) {
		case '1':
			request = "{\"type\":\"put\",\"message\": {";
			System.out.println("Ingresar destinatario");
			request += "\"target\":\"" + this.getNotNull(sc) +"\"";
			System.out.println("Ingresar remitente");
			request += ", \"sender\":\"" + this.getNotNull(sc) +"\"";
			System.out.println("Ingresar mensaje");
			request += ", \"message\":\"" + sc.nextLine() + "\"}}";
			break;
		case '2':
			request = "{\"type\":\"get\",";
			System.out.println("Ingresar nombre a quien están los mensajes");
			request += "\"target\":\"" + this.getNotNull(sc) +"\"}";
			break;
		case '3':
			this.running= false;
			this.request = "{\"type\":\"exit\"}";
			break;
		default:
			System.out.println("Se ha ingresado una opcion invalida");
		}
	}
	
	@Override
	public String toString() {
		return this.request;
	}

	public boolean isRunning() {
		return running;
	}
	
	public String getNotNull(Scanner sc) {
		String salida = "";
		while (salida.equals("")) {
			System.out.println("Ingresar un valor no nulo");
			salida = sc.nextLine();
		}
		return salida;
	}

}
