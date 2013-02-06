package com.pactera.eclipse.efficient.popup.actions;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import com.pactera.eclipse.efficient.UpdatedFileHelper;

public class ListUpdateAction extends BaseAction {

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			for (@SuppressWarnings("rawtypes")
			Iterator it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
				}
				if (project != null) {
					listUpdatedFiles(project);
				}
			}
		}

	}

	private void listUpdatedFiles(IProject project) {
		final PrintStream console = getConsole();
		try {
			UpdatedFileHelper updatedFileHelper = new UpdatedFileHelper(project);
			updatedFileHelper.addObserver(new ActionObserver(console));
			updatedFileHelper.writeUpdatedFilelist();
			console.println("---------------- list done ----------------\n");
			project.refreshLocal(2, null);
		} catch (IOException e) {
			e.printStackTrace(console);
			alert("generate file list fail.");
		} catch (CoreException e) {
		}
	}

}
