package com.pactera.eclipse.efficient;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pactera.file.filter.FilenameFilter;
import com.pactera.file.filter.LastModifiedFilter;
import com.pactera.file.util.FileUtil;

class NormalFileHolder extends AbstractFileHolder {

	public NormalFileHolder(String projectPath, Date modifedDate) {
		super(projectPath, modifedDate);
	}

	public List<String> getUpdatedFiles() {
		File[] files = FileUtil.listAllFiles(new File(projectPath), 
				new LastModifiedFilter(modifedDate),
				new FilenameFilter(false, "Entries", "Repository", "Root", "Tag", ".efficentplugin"));
		List<String> filepaths = new ArrayList<String>();
		for (File file : files) {
			filepaths.add(getListfilePath(file.getPath()));
		}
		return filepaths;
	}

}