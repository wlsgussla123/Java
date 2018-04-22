package observer;

public class Client implements Observer {
	private Subject subject;
	
	public Client(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
	}

	@Override
	public void update(double temparature) {
		System.out.println(temparature);
	}
	
	public void unscribe() {
		this.subject.deleteObserver(this);
	}
}
