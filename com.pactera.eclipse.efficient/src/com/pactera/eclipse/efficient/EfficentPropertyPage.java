package com.pactera.eclipse.efficient;

import static com.pactera.eclipse.efficient.ListConstants.CVS;
import static com.pactera.eclipse.efficient.ListConstants.LOCAL;
import static com.pactera.eclipse.efficient.ListConstants.NORMAL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.PropertyPage;

public class EfficentPropertyPage extends PropertyPage implements IWorkbenchPreferencePage {

	private IProject project;
	private PluginProperties properties;
	private Button radioLocal;
	private Button radioCVS;
	private Button radioNormal;
	private Text modifyTimeText;

	public void init(IWorkbench workbench) {
	}

	protected Control createContents(Composite parent) {
		if (properties == null) {
			project = (IProject) getElement().getAdapter(org.eclipse.core.resources.IProject.class);
			properties = new PluginProperties(project);
		}
		Composite group = new Composite(parent, SWT.NULL);
		group.setLayout(new GridLayout());
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Group typeGroup = new Group(group, SWT.NULL);
		typeGroup.setText("List Type");
		typeGroup.setLayout(new GridLayout(3, false));
		typeGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		radioLocal = new Button(typeGroup, SWT.RADIO | SWT.LEFT);
		radioLocal.setText(LOCAL);
		radioLocal.setEnabled(true);
		radioLocal.setSelection(LOCAL.equals(properties.getListType()));

		radioCVS = new Button(typeGroup, SWT.RADIO | SWT.LEFT);
		radioCVS.setText(CVS);
		radioCVS.setEnabled(true);
		radioCVS.setSelection(CVS.equals(properties.getListType()));

		radioNormal = new Button(typeGroup, SWT.RADIO | SWT.LEFT);
		radioNormal.setText(NORMAL);
		radioNormal.setEnabled(true);
		radioNormal.setSelection(NORMAL.equals(properties.getListType()));

		Group starttimeGroup = new Group(group, SWT.NONE);
		starttimeGroup.setLayout(new GridLayout(3, false));
		starttimeGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		starttimeGroup.setText("Modifed time");

		modifyTimeText = new Text(starttimeGroup, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = 100;
		modifyTimeText.setLayoutData(data);
		modifyTimeText.setText(properties.getModifyTime());
		modifyTimeText.setEnabled(true);

		Label description = new Label(starttimeGroup, SWT.NONE);
		description.setText("(Format: yyyyMMddHHmmss)");
		description.setEnabled(true);
		return group;
	}

	@Override
	public boolean performOk() {
		String type = LOCAL;
		if (radioLocal.getSelection()) {
			type = LOCAL;
		} else if (radioCVS.getSelection()) {
			type = CVS;
		} else if (radioNormal.getSelection()) {
			type = NORMAL;
		}
		properties.setListType(type);
		properties.setModifyTime(modifyTimeText.getText());
		properties.save();

		try {
			project.refreshLocal(IResource.DEPTH_ONE, null);
		} catch (CoreException e) {
		}
		return super.performOk();
	}

}
