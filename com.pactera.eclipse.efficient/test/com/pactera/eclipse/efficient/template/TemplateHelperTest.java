package com.pactera.eclipse.efficient.template;

import org.junit.Test;

public class TemplateHelperTest {

	@Test
	public void testGetFinalContent() {
		KeyValues keyValues = new KeyValues();
		keyValues.addPair("name", "TEST_NO");
		System.out.println(TemplateHelper.getFinalContent(TemplateType.SEQUENCE, keyValues));
	}

}
