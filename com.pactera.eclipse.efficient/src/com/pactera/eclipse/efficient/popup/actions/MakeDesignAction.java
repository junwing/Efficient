package com.pactera.eclipse.efficient.popup.actions;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import com.pactera.eclipse.efficient.MakeDesignHelper;

public class MakeDesignAction extends BaseAction {

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			final PrintStream console = getConsole();
			for (@SuppressWarnings("rawtypes")
			Iterator it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				if (element instanceof IFile) {
					IFile iFile = (IFile) element;
					final IProject project = iFile.getProject();
					MakeDesignHelper helper = new MakeDesignHelper(iFile.getLocation().toFile()) {
						public String getPath(String projectName) {
							return project.getParent().findMember(projectName).getLocation().toOSString();
						}
					};
					try {
						helper.addObserver(new ActionObserver(console));
						helper.toFile(helper.makeDesign());
						console.println("---------------- make done ----------------\n");
					} catch (IOException e) {
						e.printStackTrace(console);
						alert("the design file format is NOT right. ");
					}

					try {
						project.refreshLocal(2, null);
					} catch (CoreException e) {
						alert("refresh fail. ");
					}
				}
			}
		}

	}

}
