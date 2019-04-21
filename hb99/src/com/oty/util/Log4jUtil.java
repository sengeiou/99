package com.oty.util;

import org.apache.log4j.Logger;

public class Log4jUtil {

	private static Logger logger = Logger.getLogger(Log4jUtil.class.getName());;
	
	public static void error(String message, Throwable throwable) {
		logger.error(message, throwable);
		throwable.printStackTrace();
    }
	
	public static void error(String message) {
		logger.error(message);
    }
	
	public static void info(String message) {
		logger.info(message);
	}
	
}
