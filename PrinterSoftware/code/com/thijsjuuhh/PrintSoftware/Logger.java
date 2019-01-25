package com.thijsjuuhh.PrintSoftware;

import java.util.ArrayList;

public class Logger {

	private static Debug debug;
	private static ArrayList<String> nullList = new ArrayList<>();
	public static final int INFO = 0, WARNING = 1, ERROR = 2, SUCCEED = 3;

	public static void log(int type, String... messages) {
		for (String message : messages)
			if (debug == null)
				nullList.add(type + message);
			else
				debug.addMessage(type, message);
	}

	static void addDebugger(Debug debug) {
		Logger.debug = debug;
		for (String str : nullList) {
			debug.addMessage(Character.getNumericValue(str.charAt(0)), str.substring(1));
			System.out.println(str);
		}
	}

}
