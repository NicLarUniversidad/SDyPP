package com.ar.rmi.model.services.concrete;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.rmi.model.services.RemoteService;

public class TempService extends UnicastRemoteObject implements RemoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static double temperatura;
	public static String ciudad;
	private static boolean cambio = false;
	private static Logger log;
	private static int count;
	private static int vistos;
	
	
	public static void init() {
		temperatura = 20;
		ciudad = "Mercedes";
		log = LoggerFactory.getLogger(TempService.class);
		count = 0;
		vistos = 0;
	}
	
	public static void cambiar(Scanner sc) {
		try {
			sc.nextLine();
			ingresarLong(sc);
			cambio = true;
		}
		catch (Exception e) {
			log.error("Probablemete hubo un error con la clase Scanner");
		}
	}

	private static void ingresarLong(Scanner sc) {
		try {
			System.out.println("Ingresar temperatura");
			String temp = sc.nextLine();
			double t = Double.valueOf(temp);
			if (t<-273.15) {
				log.debug("Se ingreso una temperatura invalida: " + t);
				System.out.println("Valor de temperatura incorrecto");
				ingresarLong(sc);
			} else {
				log.debug("Se cambiará la temperatura de " + temperatura + " a " + t);
				temperatura = t;
			}
		}
		catch (Exception e) {
			log.debug("El usuario intento ingresar un valor no numeral");
			System.out.println("Ingresar un valor valido como temperatura");
			ingresarLong(sc);
		}
	}
	
	public static boolean isCambio() {
		if (cambio) {
			vistos++;
			if (vistos>=count) {
				vistos = 0;
				cambio = false;
				return true;
			}
		}
		return cambio;
	}

	public TempService() throws RemoteException {
		super();
		count++;
	}
	
	@Override
	protected void finalize() throws Throwable {
		count--;
		super.finalize();
	}

	public String Suscribe() throws RemoteException {
		try {
			while (!TempService.isCambio()) {
				Thread.sleep(20);
			};
			String salida= "La temperatura en " + ciudad + " es de " + temperatura +" grados celcius";
			log.debug("Servidor: [La temperatura en " + ciudad + " es de " + temperatura +" grados celcius]");
			TempService.notificarVisto();
			while (TempService.isCambio()) {
				Thread.sleep(20);
			};
		return salida;
		}
		catch (Exception e) {
			return "";
		}
	}

	private static void notificarVisto() {
		
	}
	
}
