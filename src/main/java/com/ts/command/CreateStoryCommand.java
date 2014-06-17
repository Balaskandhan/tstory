package com.ts.command;



import org.codehaus.jackson.map.ObjectMapper;

import com.ts.model.story;
import com.ts.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CreateStoryCommand {
	
	public boolean execute(story stry) {
		ConnectionProvider stryConn = new ConnectionProvider();
		DBCollection stryCollection = stryConn.getCollection();

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper.writeValueAsString(stry));
			stryCollection.insert(dbObject);
		} catch (Exception e) {
			System.out.println("ERROR during mapping book to Mongo Object");
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		CreateStoryCommand create = new CreateStoryCommand();
		story str = new story();
		str.setTitle("title1test");
		str.setStorytext("And this is my project");
		str.addstory(str.storybook, str.getStorytext());
		//str.storybook.add("And this test story lines");
		if (create.execute(str)) {
			System.out.println("SUCCESS:Book Created");
		} else {
			System.out.println("ERROR:Failed to create book");
		}
	}
	
	
	
}
