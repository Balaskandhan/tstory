package com.ts.mongo;




import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class ConnectionProvider {
	

	public DBCollection getCollection() {
		try {

			MongoClient mongo = new MongoClient("oceanic.mongohq.com", 10029);

			DB db = mongo.getDB("vibbs");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}

			boolean auth = db.authenticate("tsvibbs", "tsvibbs".toCharArray());
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
	
	



}

