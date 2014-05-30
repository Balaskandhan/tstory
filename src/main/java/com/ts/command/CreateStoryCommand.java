package com.ts.command;

import java.util.ArrayList;

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
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(stry));
			stryCollection.insert(dbObject);
		} catch (Exception e) {
			System.out.println("ERROR during mapping book to Mongo Object");
			return false;
		}

		return true;
	}

}
