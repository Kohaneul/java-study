package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static thread.util.MyLogger.log;


public class BoundedQueueV6_3 implements BoundedQueue{
    private BlockingQueue<String> queue;

    public BoundedQueueV6_3(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        try {
            //대기시간 설정 가능
            boolean result = queue.offer(data,1, NANOSECONDS);
            log("저장 시도 결과 = "+result);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            //대기시간 설정 가능
            return queue.poll(2,SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
