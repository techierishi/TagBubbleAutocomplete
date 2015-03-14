package com.example.autocompletetextviewcustomadapter;

public class KeyValue {

	String key;
	String value;
	boolean shown;

	public KeyValue() {

	}

	public KeyValue(String _key, String _value, boolean _shown) {
		this.key = _key;
		this.value = _value;
		this.shown = _shown;
	}

	public boolean isShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
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
