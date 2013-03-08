package com.pactera.eclipse.efficient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.pactera.eclipse.efficient.module.define.DesignDefination;

public interface CodeGenerater {

	/**
	 * �������<code>design</code>���ɴ��뵽ָ��Ŀ¼<code>targetProject</code>
	 * 
	 * @param design
	 * @param targetProject
	 * @return �����ļ��嵥
	 * @throws IOException 
	 */
	public List<String> generate(DesignDefination design, File targetProject) throws IOException;

}
