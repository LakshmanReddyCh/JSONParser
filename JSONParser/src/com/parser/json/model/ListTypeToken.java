package com.parser.json.model;

import java.util.ArrayList;
import java.util.List;

public class ListTypeToken implements Token {
	
	List<Token> tokens;
	public ListTypeToken() {
		tokens = new ArrayList<>();
	}
	
	
	public void add(Token token) {
		tokens.add(0,token);
	}
	
	@Override
	public String getText() {
		String res = "[";
		Token token = tokens.get(0);
		res += token.getText();
		for (int i = 1; i < tokens.size(); i++) {
			res += "," + tokens.get(i).getText();
		}
		res = res + "]";
		return res;
	}


	@Override
	public String toString() {
		return "ListTypeToken [tokens=" + tokens + "]";
	}
	
	
}
