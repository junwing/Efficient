package com.pactera.eclipse.efficient;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class MakeDesignHelperTest {

	@Test
	public void testMakeDesign2() throws IOException {
		String path = "C:\\Users\\Install\\Desktop\\02_������Ŀ��_��ҵ�������2.0_corporbank.xls";
		File file = new File(path);
		MakeDesignHelper helper = new MakeDesignHelper(file) {
			@Override
			public String getPath(String projectName) {
				return "C:\\Users\\Install\\Desktop\\corporbank";
			}
		};
		helper.makeDesign();

	}

}
