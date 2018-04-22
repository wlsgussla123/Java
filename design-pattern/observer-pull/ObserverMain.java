package observer;

public class ObserverMain {
	public static void main(String[] args) {
		WeatherStation ws = new WeatherStation();
		
		Client c1 = new Client(ws);
		Client c2 = new Client(ws);
		ws.setTemparature(10.0);
		c2.unscribe();
		ws.setTemparature(5.0);
	}
}
