package com.parser.json.model;

public class ValueTypeToken implements Token {

	String value;
	public ValueTypeToken(String value) {
		this.value = value;
	}
	
	@Override
	public String getText() {
		return value;
	}

	@Override
	public String toString() {
		return "ValueTypeToken [value=" + value + "]";
	}

	
}
