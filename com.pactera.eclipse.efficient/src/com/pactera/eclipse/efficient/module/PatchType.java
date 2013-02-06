package com.pactera.eclipse.efficient.module;

public enum PatchType {

	APP("patchApp.txt"), CODE("patchCode.txt"), HTTP("patchHttp.txt");

	private String name;

	private PatchType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
