package com.ts.mongo;




import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.ts.properties.PropertiesLookup;

public class ConnectionProvider {
	

	public DBCollection getCollection() {
		try {
			PropertiesLookup aaa = new PropertiesLookup();
			
			MongoClient mongo = new MongoClient(aaa.getProperty("mongodbURL"), 10029);

			DB db = mongo.getDB("vibbs");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}

			boolean auth = db.authenticate(aaa.getProperty("usn"), aaa.getProperty("psn").toCharArray());
			if (auth == false) {
				System.out.println("Could not authenticate");
			}

			DBCollection tstoryColl = db.getCollection("tweetstories");
			return tstoryColl;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	public static void main(String[] aaa){
		ConnectionProvider con =new ConnectionProvider();
		System.out.println(con.getCollection());
	}
	



}

