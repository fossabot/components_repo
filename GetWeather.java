/**This class fetches weather data from OWM API*/
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetWeather{
	
	public static StoreWeatherData startRequest(String zip){
		String owmURL = "http://api.openweathermap.org/data/2.5/weather?zip="+zip+"&APPID=2f660a5045f3753c503e53aa8d3cb5e3";
		StringBuilder res = new StringBuilder(); //Holds Java string 
		try {	
			URL url = new URL(owmURL); //URL to be parsed
			HttpURLConnection connect = (HttpURLConnection) url.openConnection(); //Connect to API
			connect.setRequestMethod("GET"); //Type of request 
			BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream())); //Reads from open connection 
			String ln;
		    	while ((ln = reader.readLine()) != null) { //BufferedReader to String
		    		res.append(ln);
		    	}
		    reader.close();

		    return formatJson(res.toString());
		}
		catch(Exception e){
			System.out.println("Error! Exception: " + e);
			}
		return null;
	}
	
    		private static StoreWeatherData formatJson(String json) {
        //Convert json string to object
        JsonObject mainObject = new JsonParser().parse(json).getAsJsonObject();
        //Object of main key
        JsonObject main = mainObject.getAsJsonObject("main");
        
        double highTemp = main.get("temp_max").getAsDouble();
        double lowTemp = main.get("temp_min").getAsDouble();
        String weatherCondition = mainObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
        String location = mainObject.get("name").getAsString();
        
        return new StoreWeatherData(weatherCondition, location, highTemp, lowTemp);
    }

}