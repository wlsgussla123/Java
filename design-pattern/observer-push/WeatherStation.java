package observer;

import java.util.ArrayList;

public class WeatherStation implements Subject {
	private double temparature;
	private ArrayList<Observer> observers = new ArrayList();
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	@Override
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}
	@Override
	public void notifyObservers() {
		for(Observer o : observers) {
			o.update(temparature);
		}
	}
	
	public void setTemparature(double t) {
		this.temparature = t;
		notifyObservers();
	}
}
