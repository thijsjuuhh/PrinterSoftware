package com.thijsjuuhh.PrintSoftware;

public class Logger {

	static Debug debug;
	public static final int INFO = 0, WARNING = 1, ERROR = 2, SUCCEED = 3;
		
	public static void log(int type, String...messages) {
		for(String message : messages)
			debug.addMessage(type, message);
	}
	
}
