package thread;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExam {
	private ExecutorService executorService;
	
	public CallbackExam() {
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
		);
	}
	
	private CompletionHandler<Integer, Void> callback = new CompletionHandler<Integer, Void>() {
		@Override
		public void completed(Integer result, Void attachment) {
			System.out.println("completed() 실행 : " + result);
		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			System.out.println("failed() 실행 : " + exc.toString());
		}
		
	};
	
	private void doWork(final String x, final String y) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					int ix = Integer.parseInt(x);
					int iy = Integer.parseInt(y);
					
					int result = ix + iy;
					callback.completed(result, null);
				} catch(NumberFormatException e) {
					callback.failed(e, null);
				}
			}
		};
		
		executorService.submit(task);
	}
	
	public static void main(String[] args) {
		CallbackExam callback = new CallbackExam();
		callback.doWork("1", "4");
		callback.doWork("1", "테스트");
		callback.executorService.shutdown();
	}
}
