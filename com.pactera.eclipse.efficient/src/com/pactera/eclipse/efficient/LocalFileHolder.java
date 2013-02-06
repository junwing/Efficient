package com.pactera.eclipse.efficient;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pactera.file.filter.FilenameFilter;
import com.pactera.file.filter.FilepathFilter;
import com.pactera.file.filter.LastModifiedFilter;
import com.pactera.file.util.FileUtil;

class LocalFileHolder extends AbstractPatchFileHolder {

	public LocalFileHolder(String projectPath, Date modifedDate) {
		super(projectPath, modifedDate);
	}

	public List<String> getUpdatedFiles(String folder) {
		String[] pathFregmentToExclude = {
				"classes" + File.separator,
				"WEB-INF" + File.separator + "bizs",
				"WEB-INF" + File.separator + "mvcs",
				"WEB-INF" + File.separator + "commons" };
		File[] files = FileUtil.listAllFiles(new File(projectPath, folder), 
				new LastModifiedFilter(modifedDate), 
				new FilenameFilter(false, "Entries", "Repository", "Root", "Tag"),
				new FilepathFilter(false, pathFregmentToExclude));
		List<String> filepaths = new ArrayList<String>();
		for (File file : files) {
			filepaths.add(getListfilePath(file.getPath()));
		}
		return filepaths;
	}

}