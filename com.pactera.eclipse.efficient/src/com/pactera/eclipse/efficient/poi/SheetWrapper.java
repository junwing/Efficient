package com.pactera.eclipse.efficient.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SheetWrapper {

	protected Sheet sheet;

	public SheetWrapper(Sheet sheet) {
		super();
		this.sheet = sheet;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public Cell getCell(int rowIndex, int colIndex) {
		return getRowWrapper(rowIndex).getCell(colIndex);
	}

	public String getCellString(int rowIndex, int colIndex) {
		return getCell(rowIndex, colIndex).getStringCellValue().trim();
	}

	public RowWrapper getRowWrapper(int rowIndex) {
		return new RowWrapper(getRow(rowIndex));
	}

	public RowWrapper getFirstRowWrapper() {
		return getRowWrapper(0);
	}

	public RowWrapper getLastRowWrapper() {
		return new RowWrapper(getRow(getLastRowNum()));
	}

	public Row getRow(int index) {
		return this.sheet.getRow(getFirstRowNum() + index);
	}

	public int getLastRowNum() {
		return this.sheet.getLastRowNum() - this.sheet.getFirstRowNum();
	}

	private int getFirstRowNum() {
		return this.sheet.getFirstRowNum();
	}

}
