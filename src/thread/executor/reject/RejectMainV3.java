package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.log;

public class RejectMainV3 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new MyRejectedExecutionHandler());
        //CallerRunsPolicy: 작업을 요청한 스레드에 대신 실행 -> 메인메서드에서 해당 스레드 실행
        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2"));
        executor.submit(new RunnableTask("task3"));
        executor.submit(new RunnableTask("task4"));

        executor.close();

    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        static AtomicInteger count = new AtomicInteger(0);
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int i = count.incrementAndGet();
            log("[경고] 거절된 누적 작업 수 : " +i);
        }
    }
}
