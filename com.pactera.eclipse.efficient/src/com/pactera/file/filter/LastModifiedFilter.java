package com.pactera.file.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

public class LastModifiedFilter implements FileFilter {

	private long timeMillis;

	public LastModifiedFilter(long timeMillis) {
		this.timeMillis = timeMillis;
	}

	public LastModifiedFilter(Date date) {
		this.timeMillis = date.getTime();
	}

	public long getTimeMillis() {
		return timeMillis;
	}

	public void setTimeMillis(long timeMillis) {
		this.timeMillis = timeMillis;
	}

	public boolean accept(File file) {
		return file.isFile() && file.lastModified() > timeMillis;
	}

}
