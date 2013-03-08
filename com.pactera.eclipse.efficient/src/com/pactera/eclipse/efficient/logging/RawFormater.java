package com.pactera.eclipse.efficient.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class RawFormater extends Formatter {

	private final static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public String format(LogRecord record) {
		return formatMessage(record) + LINE_SEPARATOR;
	}

}
