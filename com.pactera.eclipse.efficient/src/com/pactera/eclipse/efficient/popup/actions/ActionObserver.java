package com.pactera.eclipse.efficient.popup.actions;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

public final class ActionObserver implements Observer {
	private final PrintStream console;

	public ActionObserver(PrintStream console) {
		this.console = console;
	}

	public void update(Observable o, Object msg) {
		console.println(msg);
	}
}