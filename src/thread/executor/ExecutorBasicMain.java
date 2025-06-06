package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {

        ExecutorService es = new ThreadPoolExecutor(2,2,0,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>()); //마지막 파라미터 : 스레드가 대기하는 queue
        log("== 초기 상태 ==");
        printState(es);

        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        printState(es);

        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");
        printState(es);


        sleep(3000);
        es.shutdown();
        printState(es);


        log("== 작업 수행 완료 ==");
        printState(es);




    }
}
