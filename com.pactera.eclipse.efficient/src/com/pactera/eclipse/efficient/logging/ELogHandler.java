package com.pactera.eclipse.efficient.logging;

import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class ELogHandler extends StreamHandler {

	public ELogHandler(OutputStream out) {
		setOutputStream(out);
	}

	public void publish(LogRecord record) {
		super.publish(record);
		flush();
	}

	public void close() {
		flush();
	}

}
