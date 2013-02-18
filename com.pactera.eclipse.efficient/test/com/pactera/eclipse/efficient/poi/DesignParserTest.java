package com.pactera.eclipse.efficient.poi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.pactera.eclipse.efficient.module.db.Table;
import com.pactera.eclipse.efficient.module.define.DesignDefination;

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
	}

}
