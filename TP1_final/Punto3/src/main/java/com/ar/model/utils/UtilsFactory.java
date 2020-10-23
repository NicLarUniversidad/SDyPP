package com.ar.model.utils;

import java.io.FileInputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import com.ar.model.utils.chronometer.versionAlfa.Chronometer;
import com.ar.model.utils.logger.versionAlfa.CustomFormatter;
import com.ar.model.utils.logger.versionAlfa.CustomLogger;

public class UtilsFactory {
	static public ICustomLogger createLogger(String className) {
		return new CustomLogger(className);
	}
	
	static public Logger createLogger(Class<?> c) {
		CustomLogger log = new CustomLogger(c.getName());
		log.setLogger(c);
		return log.getLogger();
	}
	
	static public ICustomLogger createLogger() {
		
		return null;
	}
	
	static public IChronometer createChronometer() {
		return new Chronometer();
	}
	
	static public void configureLogger(Logger logger, String filePath) {
		try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("lib/logging.properties"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new StreamHandler());
        try {
            Handler fileHandler = new FileHandler( filePath, 20000, 5);
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
