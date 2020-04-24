package com.parser.json;

import com.parser.json.model.InvalidJSONString;
import com.parser.json.model.Token;

public class Main {

	public static void main(String[] args) {
		//String jsonString = "{\"name\" : \"lakshman\", \"designation\" : [\"IT\", \"CS\"], \"adress\" : { \"line1\" : \"street1\", \"line2\" : \"rcpuram\"}}";
		//String jsonString = "[\"Sunday\", \"Monday\", \"Tuesday\", \"Wednesday\", \"Thursday\", \"Friday\", \"Saturday\"]" ;
		//String jsonString = "[{\"name\":\"Ram\", \"email\":\"Ram@gmail.com\"},{\"name\":\"Bob\", \"email\":\"bob32@gmail.com\"}]";
		String jsonString = "{\"employees\":[{\"name\":\"Shyam\", \"email\":\"shyamjaiswal@gmail.com\"},{\"name\":\"Bob\", \"email\":\"bob32@gmail.com\"},{\"name\":\"Jai\", \"email\":\"jai87@gmail.com\"}]}";  
		try {
			Token token = JSONParser.parse(jsonString);
			System.out.println(token);
			System.out.println(token.getText());
		} catch (InvalidJSONString e) {
			e.printStackTrace();
		}
	}

}
