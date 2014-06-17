package com.ts.command;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;



import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.ts.model.story;
import com.ts.mongo.ConnectionProvider;

public class GetStory {
	ObjectMapper mapper = new ObjectMapper();
	public story execute(String title){

		ConnectionProvider storyConn = new ConnectionProvider();
		DBCollection stryCollection = storyConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("title", title);
		DBCursor cursor = stryCollection.find(searchQuery);
		DBObject strys = cursor.next();
		story stry = null;
		try
		{
			stry = mapper.readValue(strys.toString(), story.class);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return stry;
	}
	
	public DBObject execute2(String title){

		ConnectionProvider storyConn = new ConnectionProvider();
		DBCollection stryCollection = storyConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("title", title);
		DBCursor cursor = stryCollection.find(searchQuery);
		DBObject strys = cursor.next();
		
		return strys;
	}
	
	public static void main(String[] a){
		GetStory stry = new GetStory();
		System.out.println(stry.execute2("title1test"));
		
	}
		
}


