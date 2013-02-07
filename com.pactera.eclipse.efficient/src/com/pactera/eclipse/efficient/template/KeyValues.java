package com.pactera.eclipse.efficient.template;

import java.util.ArrayList;
import java.util.List;

/**
 * key value pairs
 * 
 * @author ruanzr
 * 
 */
public class KeyValues {

	private List<KeyValue> pairs = new ArrayList<KeyValue>();

	public void addPair(String key, String value) {
		pairs.add(new KeyValue(key, value));
	}

	public List<KeyValue> getPairs() {
		return pairs;
	}

}

/**
 * key value pair
 * 
 * @author ruanzr
 * 
 */
class KeyValue {

	private String key;
	private String value;

	public KeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
