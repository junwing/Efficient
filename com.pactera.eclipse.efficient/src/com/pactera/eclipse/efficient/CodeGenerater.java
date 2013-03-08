package com.pactera.eclipse.efficient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.pactera.eclipse.efficient.module.define.DesignDefination;

public interface CodeGenerater {

	/**
	 * 根据设计<code>design</code>生成代码到指定目录<code>targetProject</code>
	 * 
	 * @param design
	 * @param targetProject
	 * @return 生成文件清单
	 * @throws IOException 
	 */
	public List<String> generate(DesignDefination design, File targetProject) throws IOException;

}
