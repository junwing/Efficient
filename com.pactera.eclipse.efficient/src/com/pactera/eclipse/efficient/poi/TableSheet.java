package com.pactera.eclipse.efficient.poi;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;

import com.pactera.eclipse.efficient.module.db.Column;
import com.pactera.eclipse.efficient.module.db.Table;

public class TableSheet extends SheetWrapper implements Iterable<RowWrapper> {

	public TableSheet(Sheet sheet) {
		super(sheet);
	}

	public Table getTable() {
		final Table table = new Table(getCellString(1, 1), getCellString(0, 1), getCellString(2, 1), getLastRowWrapper().getCellString(1));
		for (RowWrapper row : this) {
			table.addColumn(new Column(row.getCellString(0), row.getCellString(1), row.getCellString(2), row.getCellString(3), row.getCellString(4)));
		}
		return table;
	}

	public Iterator<RowWrapper> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<RowWrapper> {

		private int cursor = 4;

		public boolean hasNext() {
			return cursor < getLastRowNum();
		}

		public RowWrapper next() {
			return getRowWrapper(cursor++);
		}

		public void remove() {
		}

	}

}
