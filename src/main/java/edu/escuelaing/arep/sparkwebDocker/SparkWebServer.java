package edu.escuelaing.arep.sparkwebDocker;
import static spark.Spark.*;

import com.google.gson.Gson;

public class SparkWebServer {
	private static ConnectMongodb mongodb = new ConnectMongodb();
	
    public static void main(String... args){
    	staticFiles.location("/public");
    	//mongodb.getStringsDB();
    	//mongodb.addString("mongodb");
        port(getPort());
        get("/logService", (request,response) -> {response.type("application/json");
        mongodb.addString(request.queryParams("cadena"));
        return new Gson().toJson(mongodb.getStringsDB());});
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}
