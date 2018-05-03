package pattern;

// 가장 기본적인 것.
class BaseSingleton {
	private static BaseSingleton instance = null;
	private BaseSingleton() {}
	
	public static BaseSingleton getInstance() {
		if(instance == null) {
			instance = new BaseSingleton();
		}
		return instance;
	}
	
	public void print() {
		System.out.println("base !");
	}
}

// 이른 초기화
class EagerSingleton {
	private static EagerSingleton instance = new EagerSingleton();
	private EagerSingleton() {}
	
	public static EagerSingleton getInstance() {
		return instance;
	}
	
	public void print() {
		System.out.println("eager !");
	}
}

// 스태틱 블록 초기화
class StaticBlock {
	private static StaticBlock instance = null;
	private StaticBlock() {}
	
	static {
		try {
			instance = new StaticBlock();
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public static StaticBlock getInstance() {
		return instance;
	}
	
	public void print() {
		System.out.println("static block !");
	}
}

class ThreadSafe {
	private static ThreadSafe instance = null;
	private ThreadSafe() {}
	
	public static synchronized ThreadSafe getInstance() {
		if(instance == null) 
			instance = new ThreadSafe();
		return instance;
	}
	
	public void print() {
		System.out.println("thread !");
	}
}

class LazyHolder {
	private LazyHolder() {}
	
	private static class Holder {
		private static LazyHolder instance = new LazyHolder();
	}
	
	public static LazyHolder getInstance() {
		return Holder.instance;
	}
	
	public void print() {
		System.out.println("lazy holder !");
	}
}

public class Main {
	public static void main(String[] args) {
		BaseSingleton bs = BaseSingleton.getInstance();
		bs.print();
		
		EagerSingleton es = EagerSingleton.getInstance();
		es.print();
		
		StaticBlock sb = StaticBlock.getInstance();
		sb.print();
		
		ThreadSafe ts = ThreadSafe.getInstance();
		ts.print();
		
		LazyHolder lh = LazyHolder.getInstance();
		lh.print();
	}
}
