package thread.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.util.MyLogger.log;

public abstract class ExecutorUtils {
    public static void printState(ExecutorService executorService){
        if(executorService instanceof ThreadPoolExecutor poolExecutor){
            int pool = poolExecutor.getPoolSize();  //pool의 갯수
            int active = poolExecutor.getActiveCount();//현재 실행되는 스레드의 갯수
            int queuedTasks = poolExecutor.getQueue().size();
            long completedTask = poolExecutor.getCompletedTaskCount();
            log("[pool = "+pool+", active = "+active+", queuedTasks = "+queuedTasks +
                     ", completedTask = "+completedTask +" ]");
        }
        else{
            log(executorService);
        }
    }
}
