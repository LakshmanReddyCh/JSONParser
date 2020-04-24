package com.parser.json;

import com.parser.json.model.InvalidJSONString;
import com.parser.json.model.Token;

import java.util.Stack;


public class JSONParser {
	
	
	public static Token parse(String jsonString) throws InvalidJSONString {
		int i = 0;
		jsonString = jsonString.trim();
		
		Stack<Token> tokenStack = new Stack<>();
		Stack<Integer> splitterStack = new Stack<>();
		while (i < jsonString.length()) {
			char c = jsonString.charAt(i);
			if (c == '{' || c == '[') {
				splitterStack.push(i);
			} else if (c == '}' || c == ']') {
				if (splitterStack.isEmpty()) {
					throw new InvalidJSONString("Invalid at character index " + i);
				}
				if (c == '}' && jsonString.charAt(splitterStack.peek()) != '{') {
					throw new InvalidJSONString("Invalid at character index " + i );
				}
				if (c == ']' && jsonString.charAt(splitterStack.peek()) != '[') {
					throw new InvalidJSONString("Invalid at character index " + i);
				}
				if (c == '}') {
					tokenStack.push(TokenCreationUtil.createMapTypeToken(jsonString.substring(splitterStack.pop() + 1, i), tokenStack));
				} else if (c == ']') {
					tokenStack.push(TokenCreationUtil.createListTypeToken(jsonString.substring(splitterStack.pop() + 1, i), tokenStack));
				}
			}
			i++;
		}
		if (!splitterStack.isEmpty()) {
			throw new InvalidJSONString("Invalid String");
		}
		return tokenStack.pop();
	}
	
	
	
}
