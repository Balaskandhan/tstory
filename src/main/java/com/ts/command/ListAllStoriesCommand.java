package com.ts.command;

import java.util.ArrayList;


import com.ts.mongo.ConnectionProvider;


import com.mongodb.DBObject;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllStoriesCommand {
	
	public ArrayList<DBObject> execute(){
		ConnectionProvider stryConn = new ConnectionProvider();
		DBCollection stryCollection = stryConn.getCollection();
		
		DBCursor cursor = stryCollection.find();
		
		ArrayList<DBObject> stry = new ArrayList<DBObject>();
		try {
		   while(cursor.hasNext()) {
			   stry.add(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		return stry;
		
	}

}
