package edu.escuelaing.arep.sparkwebDocker;
import static spark.Spark.*;

import com.google.gson.Gson;
/**
 * La clase contiene el m�todo main donde se ejecuta el programa y el getPort.
 * @author Daniel Santiago Ducuara Ardila
 *
 */
public class SparkWebServer {
	private static ConnectMongodb mongodb = new ConnectMongodb();
	/**
	 * El m�todo posee un m�todo get que retorna un json con los �ltimos 10 datos almacenados en mongodb e inserta la cadena del usuario.
	 * @param args par�metro main
	 */
    public static void main(String... args){
    	staticFiles.location("/public");
        port(getPort());
        get("/logService", (request,response) -> {response.type("application/json");
        mongodb.addString(request.queryParams("cadena"));
        return new Gson().toJson(mongodb.getStringsDB());});
    }

    /**
     * M�todo para cambiar de puerto.
     * @return el puerto
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}
