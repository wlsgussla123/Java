package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteExam {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
		);
		
		System.out.println("작업 처리 요청");
		Callable<Integer> task = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int sum = 0;
				for(int i=1; i<=10; i++) {
					sum += i;
				}
				
				return sum;
			}
		};
		
		Future<Integer> future = executorService.submit(task);
		try {
			int sum = future.get();
			System.out.println("처리 결과 : " + sum);
			System.out.println("작업 처리 완료");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
	}
}
