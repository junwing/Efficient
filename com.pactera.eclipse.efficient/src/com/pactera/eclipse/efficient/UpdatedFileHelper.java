package com.pactera.eclipse.efficient;

import java.io.IOException;
import java.util.Date;
import java.util.Observer;

import org.eclipse.core.resources.IProject;

import com.pactera.eclipse.efficient.module.PatchInfo;
import com.pactera.util.DateUtil;
import com.pactera.util.StringUtil;

public class UpdatedFileHelper {

	private String projectPath;
	private PluginProperties properties;
	private PatchInfo patchInfo;

	public UpdatedFileHelper(IProject project) {
		this.projectPath = project.getLocation().toOSString();
		this.properties = new PluginProperties(project);
		this.patchInfo = new PatchInfo(getFileHolder());
	}

	public FileHolder getFileHolder() {
		Date modDate = getStartModifedDate();
		if (properties.getListType().equals("Local")) {
			return new LocalFileHolder(projectPath, modDate);
		} else if (properties.getListType().equals("CVS")) {
			return new CVSEntriesFileHolder(projectPath, modDate);
		} else if (properties.getListType().equals("Normal")) {
			return new NormalFileHolder(projectPath, modDate);
		}
		return null;
	}

	private Date getStartModifedDate() {
		String timeStr = properties.getModifyTime();
		return (StringUtil.isEmpty(timeStr)) ? DateUtil.getToday() : DateUtil.toDateTime(StringUtil.paddingRight(timeStr, 14, '0'));
	}

	public void writeUpdatedFilelist() throws IOException {
		String modifyTime = properties.getModifyTime();
		patchInfo.toFile(projectPath, (modifyTime.length() > 0 ? "_" : "") + modifyTime + ".txt");
	}

	public void addObserver(Observer o) {
		patchInfo.addObserver(o);
	}

}

