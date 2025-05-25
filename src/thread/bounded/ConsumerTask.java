package thread.bounded;

import java.util.concurrent.BlockingQueue;

import static thread.util.MyLogger.log;

public class ConsumerTask implements Runnable{
    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String data = queue.take();
        log("[소비 완료]"+data+" <- "+queue);

    }
}
