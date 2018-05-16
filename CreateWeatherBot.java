/**This class creates the IRC Bot*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jibble.pircbot.PircBot;

public class CreateWeatherBot extends PircBot {

    private static String server = "irc.freenode.net";
    private static String channel = "#ggbmo";
    private static String name = "ggbmoWeatherBot";
    
    //Regex to Look for 5 digit ZIP
    public static Pattern regex = Pattern.compile("(\\d{5})");

    public CreateWeatherBot() {
        setName(name);
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        message = message.toLowerCase();
        String zip = "75035"; //Initialize zip with default value
        
        //Look for "weather" in message input
        if (message.contains("weather")) {
            Matcher matches = regex.matcher(message);
            if (matches.find()) {
            		zip = matches.group();
            } 
            else {
            		//ZIP not found
            		sendMessage(channel, "Cannot determine location.");
            }
            //Initiate request
            StoreWeatherData data = GetWeather.startRequest(zip);
            //If request fails
            if (data == null) {
                    sendMessage(channel, "An error occurred with the API.");
                }
            
            String weatherData = data.format();
            sendMessage(channel, weatherData);
            }
        }

    public static void main(String[] args) {
    	
        //Start up the bot
        CreateWeatherBot myBot = new CreateWeatherBot();
        
        //Debugging output
        myBot.setVerbose(true);
        
        try {
        		//Connect to IRC server
        		myBot.connect(server);
        } catch (Exception e) {
            System.out.println("Connection to "+server+" failed.");
        }
        
        	myBot.joinChannel(channel);
        	myBot.sendMessage(channel, "ggbmoWeatherBot has joined!");
    }
 
}