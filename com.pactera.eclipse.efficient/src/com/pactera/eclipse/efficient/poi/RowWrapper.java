package com.pactera.eclipse.efficient.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class RowWrapper {

	private Row row;

	public RowWrapper(Row row) {
		super();
		this.row = row;
	}

	public Row getRow() {
		return row;
	}

	public Cell getCell(int col) {
		return row.getCell(row.getFirstCellNum() + col);
	}

	public String getCellString(int col) {
		return getCell(col).getStringCellValue().trim();
	}

}
