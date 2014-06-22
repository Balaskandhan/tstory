package com.ts.command;


import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;

import com.mongodb.DBCollection;

import com.mongodb.DBObject;



import com.mongodb.util.JSON;
import com.ts.model.story;
import com.ts.mongo.*;
public class UpdateStory {
	
	
	public boolean execute(story stry){
		ConnectionProvider storyConn = new ConnectionProvider();
		DBCollection stryCollection = storyConn.getCollection();

		ObjectMapper mapper = new ObjectMapper();
		
		BasicDBObject searchQuery = new BasicDBObject();
		/**
		 * update a book - this will update only one record
		 */
		searchQuery.put("title", stry.getTitle());

		BasicDBObject newDocument = new BasicDBObject();
		//newDocument.put("titile", "Super Pants");-->>the stry hads to become this newDocument Basic DBObject
		//newDocument = (DBObject) JSON.parse(mapper.writeValueAsString(stry));
		
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper.writeValueAsString(stry));
			newDocument = (BasicDBObject)dbObject;
		} catch (Exception e) {
			System.out.println("ERROR during mapping book to Mongo Object");
			return false;
		}
		
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
	


		stryCollection.update(searchQuery, updateObj);
		return true;
	}

	public static void main(String[] a){
		ObjectMapper mapper = new ObjectMapper();
		GetStory get = new GetStory();
		story stry = get.execute("title1test");
		try{
		System.out.println(mapper.writeValueAsString(stry));}catch(Exception e) {
			e.printStackTrace();
		}
		stry.addstory(stry.storybook, "test line3 for update command");
		UpdateStory up = new UpdateStory();
	    boolean aa = up.execute(stry);
	    if(aa==true){
	    story stry1 = get.execute("title1test");
		try{
		System.out.println(mapper.writeValueAsString(stry1));}catch(Exception e) {
			e.printStackTrace();
		}}
	    else{System.out.println("error");}
	}
}
