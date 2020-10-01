package com.ar.model.utils;

import java.util.logging.Logger;

import com.ar.model.utils.chronometer.versionAlfa.Chronometer;
import com.ar.model.utils.logger.versionAlfa.CustomLogger;

public class UtilsFactory {
	static public ICustomLogger createLogger() {
		return new CustomLogger();
	}
	
	static public Logger createLogger(Class<?> c) {
		CustomLogger log = new CustomLogger();
		log.setLogger(c);
		return log.getLogger();
	}
	
	static public IChronometer createChronometer() {
		return new Chronometer();
	}
}
