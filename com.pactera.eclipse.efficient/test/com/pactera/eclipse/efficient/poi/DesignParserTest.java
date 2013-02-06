package com.pactera.eclipse.efficient.poi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.pactera.eclipse.efficient.module.db.Table;

public class DesignParserTest {

	@Test
	public void testParseDBFile() throws IOException {
		String path = "E:\\workspaces\\grcb\\��Ŀ�ĵ�\\21_������¼\\DY20120809-001_��ҵ�������\\03_������Ŀ��_��ҵ����_tables.xls";
		List<Table> tables = DesignParser.parseDBFile(new File(path));
		for (Table table : tables) {
			System.out.println(table.toSQL());
		}
	}

}
