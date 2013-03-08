package com.pactera.eclipse.efficient.popup.actions;

import java.io.PrintStream;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

public abstract class BaseAction implements IObjectActionDelegate {

	public static final String CONSOLE_NAME = "Efficient";
	protected ISelection selection;
	private Shell shell;

	public BaseAction() {
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	public static PrintStream getConsole() {
		MessageConsole console = null;
		ConsolePlugin dfPlugin = ConsolePlugin.getDefault();
		if (dfPlugin == null) {
			return System.out;
		}
		final IConsoleManager consoleManager = dfPlugin.getConsoleManager();
		for (IConsole c : consoleManager.getConsoles()) {
			if (CONSOLE_NAME.equals(c.getName())) {
				console = (MessageConsole) c;
				break;
			}
		}
		if (console == null) {
			console = new MessageConsole(CONSOLE_NAME, null);
			consoleManager.addConsoles(new IConsole[] { console });
		}
		return new PrintStream(console.newMessageStream());
	}

	protected void alert(String msg) {
		MessageDialog.openInformation(shell, "Efficent", msg);
	}

}
