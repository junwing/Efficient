package com.pactera.eclipse.efficient.module.emp;

public abstract class Elem extends AbstractElem {

	private int index;
	private String id;
	private int x;
	private int y;
	private int width;
	private int height;
	private Elem next;
	
	public Elem(String tagName, String name, int index) {
		super(tagName, name);
		this.index = index;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setArea(int x, int y, int width, int height) {
		this.setXY(x, y);
		this.width = width;
		this.height = height;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Elem getNext() {
		return next;
	}

	public void setNext(Elem next) {
		this.next = next;
	}

	protected abstract Connector getConnector();

	protected abstract void appendLocal(StringBuffer h);

	public String toXml() {
		StringBuffer xml = new StringBuffer();
		xml.append('\t').append('<').append(this.getTagName());
		if (this.id != null) {
			xml.append(" id=\"").append(this.getId()).append('\"');
		}
		xml.append(" name=\"").append(this.getName()).append('\"');
		appendLocal(xml);
		xml.append(" x=\"").append(this.getX()).append('\"');
		xml.append(" y=\"").append(this.getY()).append('\"');
		xml.append(" width=\"").append(this.getWidth()).append('\"');
		xml.append(" height=\"").append(this.getHeight()).append('\"');
		if (this.getNext() != null) {
			xml.append(">\n");
			xml.append("\t\t").append(this.getConnector().toXml()).append('\n');
			xml.append("\t</").append(this.getTagName()).append('>');
		} else {
			xml.append("/>");
		}
		xml.append('\n');
		return xml.toString();
	}

}
