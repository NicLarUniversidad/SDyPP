package com.ar.model.utils;

import java.util.logging.Logger;

public class ICustomLoggerFixed extends Logger implements ICustomLogger {

	@Override
	@Deprecated
	public void logInfo(String string, String sourceClass, String sourceMethod) {
		// TODO Auto-generated method stub
		
	}

	public ICustomLoggerFixed(String name, String resourceBundleName) {
		super(name, resourceBundleName);
		
	}

	@Override
	@Deprecated
	public void logWarning(String string, String sourceClass, String sourceMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Deprecated
	public void logError(String string, String sourceClass, String sourceMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Deprecated
	public void setLogger(Class<?> c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logInfo(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logWarning(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logError(String string) {
		// TODO Auto-generated method stub
		
	}

}
