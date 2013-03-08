package com.pactera.eclipse.efficient.template;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;

import com.pactera.eclipse.efficient.EfficientPlugin;
import com.pactera.file.util.FileUtil;

public class TemplateHelper {

	private static Map<String, String> TEMPLATE_MAP;

	static {
		TEMPLATE_MAP = new HashMap<String, String>();
		for (TemplateType type : TemplateType.values()) {
			TEMPLATE_MAP.put(type.getName(), null);
		}
	}

	private TemplateHelper() {
	}

	public static String getTemplate(TemplateType type) {
		return getTemplate(type.getName());
	}

	public static String getTemplate(String name) {
		if (!TEMPLATE_MAP.containsKey(name)) {
			return null;
		}
		String template = TEMPLATE_MAP.get(name);
		if (template == null) {
			try {
				template = getTemplateContent(name);
				TEMPLATE_MAP.put(name, template);
			} catch (IOException e) {
				// TODO
				e.printStackTrace();
			}
		}
		return template;
	}

	public static String getFinalContent(TemplateType type, KeyValues keyValues) {
		return getFinalContent(type.getName(), keyValues);
	}

	public static String getFinalContent(String name, KeyValues keyValues) {
		String template = getTemplate(name);
		if (keyValues != null && template != null) {
			for (KeyValue kv : keyValues.getPairs()) {
				template = template.replaceAll("\\{\\s*" + kv.getKey() + "\\s*\\}", kv.getValue());
			}
		}
		return template;
	}

	public static String getBizTemplate() {
		return getTemplate(TemplateType.BIZ);
	}

	public static String getOperationTemplate() {
		return getTemplate(TemplateType.OPERATION);
	}

	public static String getJspTemplate() {
		return getTemplate(TemplateType.JSP);
	}

	private static String getTemplateContent(String tplName) throws IOException {
		final String tplPathname = "template/" + tplName + ".tpl";
		final File file = new File(tplPathname);
		if (file.exists()) {
			return FileUtil.readFile2String(file);
		} else {
			URL resource = EfficientPlugin.getDefaultBundle().getResource(tplPathname);
			final URL url = FileLocator.toFileURL(resource);
			if (url != null) {
				return FileUtil.readStream2String(url.openStream());
			} else {
				String location = EfficientPlugin.getDefaultBundle().getLocation();
				int indexOf = location.indexOf(":");
				if (indexOf != -1) {
					final File tpl = new File(location.substring(indexOf - 1) + tplPathname);
					return FileUtil.readFile2String(tpl);
				}
			}
		}
		return "";
	}

}
