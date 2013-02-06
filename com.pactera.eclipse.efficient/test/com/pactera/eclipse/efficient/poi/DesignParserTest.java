package com.pactera.eclipse.efficient.poi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.pactera.eclipse.efficient.module.db.Table;

public class DesignParserTest {

	@Test
	public void testParseDBFile() throws IOException {
		String path = "E:\\workspaces\\grcb\\项目文档\\21_开发记录\\DY20120809-001_企业基金代销\\03_网银项目组_企业基金_tables.xls";
		List<Table> tables = DesignParser.parseDBFile(new File(path));
		for (Table table : tables) {
			System.out.println(table.toSQL());
		}
	}

}
