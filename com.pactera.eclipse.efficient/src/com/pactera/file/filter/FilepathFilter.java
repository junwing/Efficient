package com.pactera.file.filter;

import java.io.File;
import java.io.FileFilter;

public class FilepathFilter implements FileFilter {
	private boolean include = true;

	private String[] fregments;

	public FilepathFilter() {
	}

	public FilepathFilter(boolean include, String... fregments) {
		this.include = include;
		this.fregments = fregments;
	}

	public String[] getNames() {
		return fregments;
	}

	public void setNames(String[] names) {
		this.fregments = names;
	}

	public boolean accept(File file) {
		if (include) {
			for (String fregment : fregments) {
				if (file.getPath().indexOf(fregment) == -1) {
					return false;
				}
			}
		} else {
			for (String fregment : fregments) {
				if (file.getPath().indexOf(fregment) != -1) {
					return false;
				}
			}
		}
		return true;
	}
}
