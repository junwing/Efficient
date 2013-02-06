package com.pactera.eclipse.efficient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractPatchFileHolder extends AbstractFileHolder{

	public static final String[] DIRECTORIES = {"JavaSource", "designFiles", "WebContent"};

	public AbstractPatchFileHolder(String projectPath, Date modifedDate) {
		super(projectPath, modifedDate);
	}

	public List<String> getUpdatedFiles() {
		List<String> files = new ArrayList<String>();
		for (String directory : DIRECTORIES) {
			files.addAll(getUpdatedFiles(directory));
		}
		return files;
	}

	protected abstract List<String> getUpdatedFiles(String directory);

	
}
