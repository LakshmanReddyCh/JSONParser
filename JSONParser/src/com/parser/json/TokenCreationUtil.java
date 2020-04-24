package com.parser.json;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.parser.json.model.InvalidJSONString;
import com.parser.json.model.ListTypeToken;
import com.parser.json.model.MapTypeToken;
import com.parser.json.model.Token;
import com.parser.json.model.ValueTypeToken;

public class TokenCreationUtil {
	
	
	public static ListTypeToken createListTypeToken(String json, Stack<Token> ts) throws InvalidJSONString {
		ListTypeToken token = new ListTypeToken();
		
		Queue<Character> jq = new LinkedList<>();
		for (char c : json.toCharArray()) {
			jq.offer(c);
		}
		
		while (!jq.isEmpty()) {
			Token value = getValue(jq, ts);
			token.add(value);
		}
		return token;
	}
	
	public static MapTypeToken createMapTypeToken(String json, Stack<Token> ts) throws InvalidJSONString {
		MapTypeToken token = new MapTypeToken();
		
		Queue<Character> jq = new LinkedList<>();
		for (char c : json.toCharArray()) {
			jq.offer(c);
		}
		while (!jq.isEmpty()) {
			String name = getText(jq);
			if (jq.poll() != ':') {
				throw new InvalidJSONString("Exception");
			}
			while (!jq.isEmpty() && jq.peek() == ' ') {
				jq.poll();
			}
			Token val = getValue(jq, ts);
			token.put(name, val);
			while (!jq.isEmpty() && jq.peek() == ' ') {
				jq.poll();
			}
			if (!jq.isEmpty() && jq.peek() == ',') {
				jq.poll();
			}
			while (!jq.isEmpty() && jq.peek() == ' ') {
				jq.poll();
			}
		}
		return token;
	}
	
	private static String getText(Queue<Character> jq) throws InvalidJSONString {
		while (jq.peek() == ' ') {
			jq.poll();
		}
		if (jq.poll() != '\"') {
			System.out.println(jq);
			throw new InvalidJSONString("Exception");
		}
		String name = "";
		while (jq.peek() != '\"') {
			name += jq.poll();
		}
		jq.poll();
		while (jq.peek() != null && jq.peek() == ' ') {
			jq.poll();
		}
		if (jq.peek() != null && jq.peek() == ',') {
			jq.poll();
		}
		while (jq.peek() != null && jq.peek() == ' ') {
			jq.poll();
		}
		return name;
	}
	
	private static Token getValue(Queue<Character> jq, Stack<Token> ts) throws InvalidJSONString {
		if (jq.peek() == '\"') {
			return new ValueTypeToken(getText(jq));
		} else if (jq.peek() == '{') {
			jq.poll();
			int opens = 1;
			while (opens != 0) {
				if (jq.peek() == '{') {
					opens++;
				} else if (jq.peek() == '}') {
					opens--;
				}
				jq.poll();
			}
			while (jq.peek() != null && jq.peek() == ' ') {
				jq.poll();
			}
			if (!jq.isEmpty() && jq.peek() == ',') {
				jq.poll();
			}
			while (!jq.isEmpty() && jq.peek() == ' ') {
				jq.poll();
			}
			return ts.pop();
		} else if (jq.peek() == '[') {
			jq.poll();
			int opens = 1;
			while (opens != 0) {
				if (jq.peek() == '[') {
					opens++;
				} else if (jq.peek() == ']') {
					opens--;
				}
				jq.poll();
			}
			while (jq.peek() != null && jq.peek() == ' ') {
				jq.poll();
			}
			if (!jq.isEmpty() && jq.peek() == ',') {
				jq.poll();
			}
			while (!jq.isEmpty() && jq.peek() == ' ') {
				jq.poll();
			}
			return ts.pop();
		}
		throw new InvalidJSONString("Exception");
	}
}