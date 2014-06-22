package com.ts.command;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ts.model.story;
import com.ts.mongo.ConnectionProvider;

public class SearchStory {
	static ObjectMapper mapper = new ObjectMapper();
	public ArrayList<DBObject> execute(String str){
		
		String s = ".*";
		str =s.concat(str);
		str = str.concat(s);
		
		ConnectionProvider storyConn = new ConnectionProvider();
		DBCollection stryCollection = storyConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("title", java.util.regex.Pattern.compile(str));
		DBCursor cursor = stryCollection.find(searchQuery);
		ArrayList<DBObject> strys = new ArrayList<DBObject>();
		try {
			   while(cursor.hasNext()) {
				   strys.add(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
		
		return strys;
	}

	public static void main(String[] aa){
		SearchStory get = new SearchStory();
		
		System.out.println(get.execute("girl"));
	}
}
