package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Calculator {
	int total;
	public Calculator() {}
	
	public void addTotal(int value) {
		total += value;
	}
}

public class ExecuteExam {
	static class Task implements Runnable {
		Calculator cal;
		
		public Task(Calculator cal) {
			this.cal = cal;
		}
		@Override
		public void run() {
			int sum = 0;
			for(int i=1; i<=10; i++) {
				sum += i;
			}
			this.cal.addTotal(sum);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
		);
		
		Calculator cal = new Calculator();
		Runnable task1 = new Task(cal);
		Runnable task2 = new Task(cal);
		Future<Calculator> future = executorService.submit(task1, cal);
		future = executorService.submit(task2, cal);
		try {
			cal = future.get();
			System.out.println("처리 결과 : " + cal.total);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
	}
}

