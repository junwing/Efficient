package com.pactera.eclipse.efficient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.eclipse.core.resources.IProject;

import com.pactera.file.util.FileUtil;
import com.pactera.util.StringUtil;

public class PluginProperties {

	private static final String MODIFY_TIME = "efficent.modify.time";
	private static final String LIST_TYPE = "efficent.list.type";
	private static final String PROPERTIES_FILENAME = ".efficentplugin";
	private IProject project;
	private Properties prop;

	public PluginProperties(IProject project) {
		this.project = project;
		prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(getPropertiesFile());
			prop.load(in);
		} catch (IOException e) {
		} finally {
			FileUtil.close(in);
		}
	}

	private File getPropertiesFile() {
		return this.project.getLocation().append(PROPERTIES_FILENAME).toFile();
	}

	public void save() {
		OutputStream out = null;
		try {
			out = new FileOutputStream(getPropertiesFile());
			prop.store(out, "ruanzr");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FileUtil.close(out);
		}
	}

	public String getModifyTime() {
		return StringUtil.nvl(prop.getProperty(MODIFY_TIME));
	}

	public void setModifyTime(String time) {
		prop.setProperty(MODIFY_TIME, time);
	}

	public String getListType() {
		String type = prop.getProperty(LIST_TYPE);
		if (StringUtil.isEmpty(type)) {
			return ListConstants.LOCAL;
		}
		return type;
	}

	public void setListType(String type) {
		prop.setProperty(LIST_TYPE, type);
	}

}
