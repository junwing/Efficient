package com.pactera.eclipse.efficient.template;

import java.util.HashSet;
import java.util.Set;

/**
 * key value pairs
 * 
 * @author ruanzr
 * 
 */
public class KeyValues {

	private Set<KeyValue> pairs = new HashSet<KeyValue>();

	public void addPair(String key, String value) {
		pairs.add(new KeyValue(key, value));
	}

	public Set<KeyValue> getPairs() {
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyValue other = (KeyValue) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
