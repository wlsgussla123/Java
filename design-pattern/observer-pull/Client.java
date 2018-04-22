package observer;

public class Client implements Observer {
	private Subject subject;
	
	public Client(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
	}

	@Override
	public void update(Subject subject) {
		WeatherStation ws = (WeatherStation) subject;
		System.out.println(ws.getTemparature());
	}
	
	public void unscribe() {
		this.subject.deleteObserver(this);
	}
}
