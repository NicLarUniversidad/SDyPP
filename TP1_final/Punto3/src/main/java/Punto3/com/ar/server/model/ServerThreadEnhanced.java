package Punto3.com.ar.server.model;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Punto3.com.ar.server.model.actions.ActionFactory;
import Punto3.com.ar.server.model.actions.IAction;

public class ServerThreadEnhanced extends ServerThread {

	protected Logger logger;

	public ServerThreadEnhanced(Socket client) throws IOException{
		super(client);
		this.logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public ServerThreadEnhanced(Socket client, Logger logger) throws IOException{
		super(client);
		this.logger = logger;
	}

	@Override
	public void run() {
		this.out.println("Se ha conectado con el servidor");
		try {
			String request; 
			boolean conected = true;
			while (conected) {
				request = this.in.readLine();
				logger.info("Se ha recibido del cliente {" + client.getInetAddress() +
						":" + client.getPort()+ "}:" + request);
				IAction a = ActionFactory.createAction(request, conected);
				String r = a.excecute();
				logger.info("Se contesto a la consulta anterior con: " + r);
				this.out.println(r);
				conected = !(r.equals("{\"type\":\"ack\"}"));
			}
		} catch (IOException e) {
			logger.error("Ha ocurrido un error de lectura escritura");
		}
	}

}
