package com.parser.json.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTypeToken implements Token {

	Map<String, Token> tokensMap;
	
	public MapTypeToken() {
		tokensMap = new HashMap<>();
	}
	
	public void put(String key, Token token) {
		tokensMap.put(key, token);
	}
	
	public String getText() {
		String res =  "{";
		Set<Map.Entry<String, Token>> entries = tokensMap.entrySet();
		int i = 0;
		for (Map.Entry<String, Token> entry : entries) {
			if (i == 0) {
				res += (entry.getKey() + " : " + entry.getValue().getText());
				i++;
			} else {
				res += (", " + entry.getKey() + " : " + entry.getValue().getText());
			}
		}
		res = res + "}";
		return res;
	}

	@Override
	public String toString() {
		return "MapTypeToken [tokensMap=" + tokensMap + "]";
	}
	
	
}
