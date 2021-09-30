package edu.escuelaing.arep.sparkwebDocker;

import com.mongodb.client.MongoClients;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Date;
import java.util.Iterator;

import org.bson.Document;

public class ConnectMongodb {
	public ConnectMongodb() {
		getStringsDB();
		//addString("Mongodb");
	}

	public void getStringsDB() {
		int contStringsDB=0;
		int index = 0;
		MongoClient client = (MongoClient) MongoClients.create("mongodb+srv://db:db@cluster0.nznji.mongodb.net/db?retryWrites=true&w=majority");
		MongoDatabase database = client.getDatabase("db");
		MongoCollection<Document> collection = database.getCollection("cadenas");
		FindIterable<Document> iterDocumentsDb = collection.find();
		Iterator iterDocuments = iterDocumentsDb.iterator();
		int countDocuments = (int) collection.count();
		while (iterDocuments.hasNext() && contStringsDB<10) {
			System.out.println("cant: "+countDocuments);
			System.out.println("desde: "+(countDocuments-10));
			System.out.println("contador"+index);
			if(index>=(countDocuments-10)) {
				System.out.println("entra");
				contStringsDB+=1;
				System.out.println(iterDocuments.next());
			}else {
				iterDocuments.next();
				index++;
			}
			
	    	}
	}

	public void addString(String userString) {
		MongoClient client = (MongoClient) MongoClients.create("mongodb+srv://db:db@cluster0.nznji.mongodb.net/db?retryWrites=true&w=majority");
		MongoDatabase database = client.getDatabase("db");
		MongoCollection<Document> collection = database.getCollection("cadenas");
		Document document = new Document();
		document.append("cadena", userString);
		document.append("fecha", new Date());
		collection.insertOne(document);
		System.out.println("finish");
	}

}
