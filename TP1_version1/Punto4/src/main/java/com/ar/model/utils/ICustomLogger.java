package com.ar.model.utils;


/**
 * 
 * Interfaz de los loggers modificados para los TP de sistemas distribuidos y programación paralela.
 * 
 * Representa a un logger en memoria, que en simultáneo persistirá los registros. 
 * 
 * 
 * @author Esteban Nicolás Larena
 *
 */

public interface ICustomLogger {
	
	/**
	 * Muestra un log por salida estandar y luego lo persiste.
	 * 
	 * @param string : lo que loggeará...
	 */
	
	public void logInfo(String string, String sourceClass, String sourceMethod);
	public void logInfo(String string);

	/**
	 * Muestra/Persiste un warning.
	 * 
	 * @param string : lo que loggeará...
	 */
	
	public void logWarning(String string, String sourceClass, String sourceMethod);
	public void logWarning(String string);
	
	/**
	 * Muestra/Persiste un error.
	 * 
	 * @param string : lo que loggeará...
	 */
	
	public void logError(String string, String sourceClass, String sourceMethod);
	public void logError(String string);
	
	public void setLogger(Class<?> c);
	
}
