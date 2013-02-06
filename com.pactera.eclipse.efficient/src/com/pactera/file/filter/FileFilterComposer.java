package com.pactera.file.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileFilterComposer implements FileFilter{

	private List<FileFilter> filters = new ArrayList<FileFilter>();

	public FileFilterComposer() {
	}

	public FileFilterComposer(FileFilter... filters) {
		for (FileFilter fileFilter : filters) {
			this.filters.add(fileFilter);
		}
	}

	public boolean accept(File pathname) {
		for (FileFilter filter : filters) {
			if(!filter.accept(pathname)) {
				return false;
			}
		}
		return true;
	}

}
