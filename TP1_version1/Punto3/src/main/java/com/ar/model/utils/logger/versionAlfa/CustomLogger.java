package com.ar.model.utils.logger.versionAlfa;

import java.io.FileInputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import com.ar.model.utils.ICustomLogger;

/**
 * 
 * Clase destinada al registro de actividades y operaciones.
 * 
 * Indiferentemente de la implementación, representa un logger en memoria
 * -se podría usar la clase Logger-
 * y otro logger persistente.
 * 
 * 
 * 
 * @author Esteban Nicolás Larena
 *
 */

public class CustomLogger implements ICustomLogger {
	
	protected Logger logger;
	
	public void setLogger(Class<?> c) {
		this.logger = Logger.getLogger(c.getName());
	}

	public CustomLogger(String className) {
		super();
		this.logger = Logger.getLogger(className);
		try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("lib/logging.properties"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new StreamHandler());
        try {
        	String path = "logs/logger.log";
            Handler fileHandler = new FileHandler(path, 20000, 5);
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public Logger getLogger() {
		return logger;
	}

	protected void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void logInfo(String string, String sourceClass, String sourceMethod) {
		logger.logp(Level.INFO, sourceClass, sourceMethod, string);
	}

	public void logWarning(String string, String sourceClass, String sourceMethod) {
		logger.logp(Level.WARNING, sourceClass, sourceMethod, string);
	}

	public void logError(String string, String sourceClass, String sourceMethod) {
		logger.logp(Level.SEVERE, sourceClass, sourceMethod, string);
	}

	@Override
	public void logInfo(String string) {
		logger.info(string);
	}

	@Override
	public void logWarning(String string) {
		logger.warning(string);
	}

	@Override
	public void logError(String string) {
		logger.severe(string);
	}
	
}
