package com.ar.RMI.model.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.RMI.model.services.RemoteService;

public class Client {
	
	ArrayList<Integer> a;
    ArrayList<Integer> b;
    Integer[] v1;
    Integer[] v2;
    Integer[] r;
	Scanner sc;
	private Logger log;
	
	
	public void run(String add, int port) {
		try {
			setLog(LoggerFactory.getLogger(this.getClass()));
			sc = new Scanner(System.in);
            RemoteService srv = (RemoteService) Naming.lookup("//" + add + ":" + port + "/Vector");
            boolean running = true;
            while(running) {
            	System.out.println("Seleccionar una operacion\n"
            			+ "1 - sumar vectores\n"
            			+ "2 - restar vectores");
            	String op = sc.nextLine();
            	log.debug("Cliente ingreso: " + op);
            	switch (op.charAt(0)) {
            	case '1':
	            	cargarVectores();
	            	vecToArray();
            		notificar(v1,v2);
            		r = srv.addVector(v1, v2);
            		notificar(r);
            		break;
            	case '2':
	            	cargarVectores();
	            	vecToArray();
            		notificar(v1,v2);
            		r = srv.susVector(v1, v2);
            		notificar(r);
            		break;
            	default:
            		System.out.println("Se ha ingresado una opcion invalida");
            	}
            }
            sc.close();
        } catch(NotBoundException e) {
        	System.out.println("NotBoundException");
        } catch(RemoteException e) {
        	System.out.println("RemoteException");
        }catch(MalformedURLException e) {
        	System.out.println("MalformedURLException");
        }
		
	}
	
	private void notificar(Integer[] v1, Integer[] v2) {
		String o = "Vectores: v1:{";
		if (v1 == null)
			o+="null";
		else
			for (int i = 0 ; i < v1.length ; i++ )
				o+=v1[i]+", ";
		o+=", v2 :{";
		if (v2 == null)
			o+="null";
		else
			for (int i = 0 ; i < v2.length ; i++ )
				o+=v2[i]+", ";
		o+="}";
		System.out.println(o);
		log.debug(o);
	}
	
	private void notificar(Integer[] r) {
		String o = "Recibido: r:{";
		if (r == null)
			o+="null";
		else
			for (int i = 0 ; i < r.length ; i++ )
				o+=r[i]+", ";
		o+="}";
		System.out.println(o);
		log.debug(o);
	}

	public void vecToArray() {
		v1 = new Integer[a.size()];
		for (int i = 0 ; i<a.size() ; i++) {
			v1[i] = a.get(i);
		}
		v2 = new Integer[b.size()];
		for (int i = 0 ; i<b.size() ; i++) {
			v2[i] = b.get(i);
		}
	}

	public void cargarVectores() {
		a = new ArrayList<Integer>();
        b = new ArrayList<Integer>();
        System.out.println("Ingresar vector 1, ingrese un valor no entero para terminar de cargar el vector");
    	String v = sc.nextLine();
    	log.debug("Cliente ingreso: " + v);
    	boolean next = true;
    	while (next) {
    		try {
    			a.add(Integer.parseInt(v));
    			System.out.println("Ingresar otro valor entero");
    			v = sc.nextLine();
    	    	log.debug("Cliente ingreso: " + v);
    		} catch (Exception  e) {
    			next = false;
    		}
    	}
    	System.out.println("Ingresar vector 2, ingrese un valor no entero para terminar de cargar el vector");
    	v = sc.nextLine();
    	log.debug("Cliente ingreso: " + v);
    	next = true;
    	while (next) {
    		try {
    			b.add(Integer.parseInt(v));
    			System.out.println("Ingresar otro valor entero");
    			v = sc.nextLine();
    	    	log.debug("Cliente ingreso: " + v);
    		} catch (Exception  e) {
    			next = false;
    		}
    	}
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
}
