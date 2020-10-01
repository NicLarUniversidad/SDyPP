package com.ar.model.utils;

/**
 * 
 * Abstracción de las clases destinadas a tomar tiempos.
 * 
 * 
 * @author Esteban Nicolás Larena
 *
 */

public interface IChronometer {
	
	public void reset();
	public void start();
	public void stop();
	public String getTime();

}
