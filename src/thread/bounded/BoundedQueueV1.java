package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue{
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if(queue.size()==max){
            log("[put] 큐가 가득 참, 버림");
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() { //한번에 하나의 스레드만 접근
        if(queue.isEmpty()){
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
