package com.example.autocompletetextviewcustomadapter;

public class KeyValue {

	String key;
	String value;

	public KeyValue() {

	}

	public KeyValue(String _key, String _value) {
		this.key = _key;
		this.value = _value;
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

	@Override
	public String toString() {
		return "[{\" key\" : \"" + key + "\"},{\" value\" : \"" + value
				+ "\"}]";
	}

}
