/**Class stores weather data*/
public class StoreWeatherData {

	private String weather;
    private String location;
    private double high;
    private double low;

    public StoreWeatherData(String weather, String location, double high, double low) {
    		setWeather(weather); 
    		setLocation(location);
        setHigh(high);
        setLow(low);
    }
    
    public void setWeather(String weather) {
        this.weather = weather.toLowerCase();
    }
   
    public void setLocation(String location) {
        this.location = location;
    }

    public void setHigh(double high) {
        this.high = toFahrenheit(high);
    }

    public void setLow(double low) {
        this.low = toFahrenheit(low);
    }

    private double toFahrenheit(double kelv) {
        return (kelv-273.15)*1.8 + 32;
    }
 
    public String format() {
        return String.format("Currently %s in %s. High will be %.1f°F and low will be %.1f°F.", weather, location, high, low);
    }
}