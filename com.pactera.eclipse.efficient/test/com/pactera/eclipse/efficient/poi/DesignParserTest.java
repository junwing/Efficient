package com.pactera.eclipse.efficient.poi;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.pactera.eclipse.efficient.module.db.Table;
import com.pactera.eclipse.efficient.module.define.DesignDefination;
import com.pactera.eclipse.efficient.module.define.MenuDefination;

public class DesignParserTest {

	@Test
	public void testParseDBFile() throws IOException {
		String path = "E:\\workspaces\\grcb\\��Ŀ�ĵ�\\21_������¼\\DY20120809-001_��ҵ�������\\03_������Ŀ��_��ҵ����_tables.xls";
		List<Table> tables = DesignParser.parseDBFile(new File(path));
		for (Table table : tables) {
			System.out.println(table.toSQL());
		}
	}

	@Test
	public void testParseDBFileForSequence() throws IOException {
		String path = "E:\\workspaces\\grcb\\��Ŀ�ĵ�\\21_������¼\\DY20120809-001_��ҵ�������\\03_������Ŀ��_��ҵ����_tables.xls";
		List<Table> tables = DesignParser.parseDBFile(new File(path));
		for (Table table : tables) {
			System.out.println(table.toSequence());
		}
	}

	@Test
	public void testParseDesignFile() throws IOException {
		String path = "E:\\workspaces\\runtime-EclipseApplication\\test\\02_������Ŀ��_�ڹܹ����Ż�_innermanage.xls";
		DesignDefination designFile = DesignParser.parseDesignFile(new File(path));
		System.out.println(designFile);
		System.out.println(designFile.getVersion() == 0);
	}

	@Test
	public void testParseDesignFile2() throws IOException {
		String path = "C:\\Users\\Install\\Desktop\\02_������Ŀ��_��ҵ�������2.0_corporbank.xls";
		DesignDefination designFile = DesignParser.parseDesignFile(new File(path));
		System.out.println(designFile);
		System.out.println(designFile.getVersion() == 0.1);
		Map<String, MenuDefination> menus = designFile.getMenus();
		for (String bsnCode: menus.keySet()) {
			MenuDefination md = menus.get(bsnCode);
			System.out.println(md);
		}
	}

}
