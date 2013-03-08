package com.pactera.eclipse.efficient.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.pactera.eclipse.efficient.popup.actions.BaseAction;

public final class ELog {

	public static final Logger logger = Logger.getLogger("Efficient");

	static {
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.FINE);
		ELogHandler handler = new ELogHandler(BaseAction.getConsole());
		handler.setFormatter(new RawFormater());
		logger.addHandler(handler);
	}

	private ELog() {
	}

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void warning(String msg) {
		logger.warning(msg);
	}

}
