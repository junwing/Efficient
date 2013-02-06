package com.pactera.eclipse.efficient.popup.actions;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import com.pactera.eclipse.efficient.FileMergeHelper;

public class MergeListAction extends BaseAction {

	private IProject project;

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		List<String> folders = new ArrayList<String>();
		if (selection instanceof IStructuredSelection) {
			for (@SuppressWarnings("rawtypes")
			Iterator it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				if (element instanceof IFolder) {
					IFolder folder = (IFolder) element;
					folders.add(folder.getFolder("/").getLocation().toOSString());
					if (this.project == null) {
						this.project = folder.getProject();
					}
				}
			}
		}
		final PrintStream console = getConsole();
		FileMergeHelper helper = new FileMergeHelper(folders);
		try {
			helper.addObserver(new ActionObserver(console));
			helper.mergeAllList();
			helper.mergeAllSql();
			console.println("---------------- merge done ----------------\n");
			project.refreshLocal(2, null);
		} catch (IOException e) {
			e.printStackTrace(console);
			alert("merge list fail. ");
		} catch (CoreException e) {
			alert("refresh fail. ");
		}
	}

}
