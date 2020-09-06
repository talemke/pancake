package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class StaticLoggerBinder implements ILoggerFactory {
	public static volatile java.util.logging.Logger REDIRECT_TO = null;
	private static final StaticLoggerBinder INSTANCE = new StaticLoggerBinder();
	private final LoggerImpl logger = new LoggerImpl();

	private StaticLoggerBinder() {
	}

	public static StaticLoggerBinder getSingleton() {
		return INSTANCE;
	}

	public ILoggerFactory getLoggerFactory() {
		return this;
	}

	@Override
	public Logger getLogger(String name) {
		return logger;
	}

}
