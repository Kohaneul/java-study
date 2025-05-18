package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue{
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override

    public synchronized void put(String data) {
        while(queue.size()==max){
            log("[put] 큐가 가득 참, 생산자 대기");
            try {
                wait(); //RUNNABLE -> WAITING, *락 반납
                log("[put] 생산자 꺠어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");
        notify();   //생산자가 소비자에게 알려줌 대기스레드 WAIT->BLOCKED
    }

    @Override
    public synchronized String take() { //한번에 하나의 스레드만 접근
        while(queue.isEmpty()){
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] 소비자 데이터 획득, notify() 호출");
        notify();   //대기스레드 -> WAIT->BLOCKED로 변경
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
