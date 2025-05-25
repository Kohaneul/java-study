package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static thread.util.MyLogger.log;


public class BoundedQueueV6_4 implements BoundedQueue{
    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
      queue.add(data);  //버퍼가 가득차면 -> java.lang.IllegalStateException : Queue full
    }

    @Override
    public String take() {
        return queue.remove();  //버퍼에 데이터가 없으면 -> java.util.NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
