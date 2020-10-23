package Punto3.com.ar.server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.ar.model.utils.ICustomLogger;
import com.ar.model.utils.UtilsFactory;

public class ServerThread implements Runnable {

	protected Socket client;
	protected boolean running;
	protected BufferedReader in;
	protected PrintWriter out;
	protected ICustomLogger logger;
	
	public ServerThread(Socket client) throws IOException{
		this.client = client;
		this.running = true;
		this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		this.out = new PrintWriter(client.getOutputStream(), true);
		this.logger = UtilsFactory.createLogger(this.getClass().getName());
	}

	public void run() {
		try {
			while(this.running) {
				out.println("Se ha conectado con el servidor.\n"
						+ "Selecccionar la opción a correr\n"
						+ "1 - Enviar un mensaje\n"
						+ "2 - Leer un mensaje\n"
						+ "3 - Salir\n");
				String op = in.readLine();
				switch(op.charAt(0)) {
				case '1':
					this.recibirMensaje();
					break;
				case '2':
					this.devolverMensajes();
					break;
				case '3':
					this.running=false;
					break;
				default:
					out.println("Se ha seleccionado una opción inválida.\n"
							+ "Volver a seleccionar una opción");
				}
			}
			out.println("Ha salido del servidor");
			
		}
		catch(Exception e) {
			out.println("Ha salido del servidor");
		}
	}

	private void devolverMensajes() {
		// TODO Auto-generated method stub
		
	}

	private void recibirMensaje() throws IOException{
		out.println("Ingresar mensaje\n"
				+ "Remitente:\n");
		
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

}
