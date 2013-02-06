package com.pactera.eclipse.efficient;

import java.util.Date;

public abstract class AbstractFileHolder implements FileHolder {
	protected String projectPath;
	protected Date modifedDate;

	public AbstractFileHolder(String projectPath, Date modifedDate) {
		this.projectPath = projectPath;
		this.modifedDate = modifedDate;
	}

	protected String getListfilePath(String filepath) {
		return filepath.substring(projectPath.length() + 1);
	}

}
