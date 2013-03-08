package com.pactera.eclipse.efficient;

public class GenerateFactory {

	public static CodeGenerater getGenerater(String projectName) {
		if("corporbank".equals(projectName)) {
			return new CBCodeGenerater();
		}
		return new CommonCodeGenerater(projectName);
	}

}
