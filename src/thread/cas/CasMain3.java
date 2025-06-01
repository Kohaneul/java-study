package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class CasMain3 {
    private static final int THREAD_COUNT = 100;
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = "+atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println("result = " + result);

    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result = false;
        do{
            //현재 값을 읽는다
            getValue = atomicInteger.get(); //t0 : 0 , t1 : 0
//            sleep(100); //스레드 동시 실행을 위한 대기
            log("getValue = "+getValue);
            //다른 스레드가 값을 증가시키지 않았을때만 증가시킴
            result = atomicInteger.compareAndSet(getValue,getValue +1);
            log("result : "+result);
        }
        while(!result); //false면 종료  , true면 계속 실행
        return getValue+1;  //atomicInteger.get()이면 다른 스레드가 증가시킨 값을 반환할 수도 있음
    }

}
