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
	public void notifyObserver(Observer o) {
		o.update(this);
	}
	
	public void setTemparature(double t) {
		this.temparature = t;
		for(Observer o : observers) {
			notifyObserver(o);
		}
	}
	
	public double getTemparature() {
		return temparature;
	}
}
