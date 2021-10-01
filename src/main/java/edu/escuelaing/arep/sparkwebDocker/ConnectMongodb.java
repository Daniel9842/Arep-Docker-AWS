package edu.escuelaing.arep.sparkwebDocker;

import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.bson.Document;
/**
 * La clase crea una conexión a una base de datos en mongodb
 * @author Daniel Santiago Ducuara Ardila
 *
 */
public class ConnectMongodb {
	
	private MongoClient client = (MongoClient) MongoClients.create("mongodb+srv://db:db@cluster0.nznji.mongodb.net/db?retryWrites=true&w=majority");
	private MongoDatabase database = client.getDatabase("db");
	private MongoCollection<Document> collection = database.getCollection("cadenas");
	
	/**
	 * Este método extrae los datos de la base de datos en mongodb.
	 * @return una lista con los últimos 10 datos.
	 */
	public ArrayList<String> getStringsDB() {
		int contStringsDB=0;
		int index = 0;	
		ArrayList<String> Jsonarray = new ArrayList<String>();
		FindIterable<Document> iterDocumentsDb = collection.find();
		Iterator<Document> iterDocuments = iterDocumentsDb.iterator();
		int countDocuments = (int) collection.countDocuments();;
		while (iterDocuments.hasNext() && contStringsDB<10) {
			if(index>=(countDocuments-10)) {
				contStringsDB+=1;
				String json = iterDocuments.next().toJson();
				json = "{"+json.substring(json.indexOf("cadena")-1,json.length());
				Jsonarray.add(json.replaceAll("\"", ""));
				System.out.println(json);
			}else {
				iterDocuments.next();
				index++;
				//
			}	
	    	}
		return Jsonarray;
	}
	
	/**
	 * Registra la cadena escrita por el usuario en la base de datos con la fecha del registro.
	 * @param userString es la cadena enviada por el usuario.
	 */
	public void addString(String userString) {
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  
		Document document = new Document();
		document.append("cadena", userString);
		document.append("fecha", strDate);
		collection.insertOne(document);
	}
}
