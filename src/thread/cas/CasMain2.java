package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.log;

public class CasMain2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = "+atomicInteger.get());

        //incrementAndGet 구현
       int resultValue1= incrementAndGet(atomicInteger);
       log("resultValue1 = "+resultValue1);

        //incrementAndGet 구현
        int resultValue2= incrementAndGet(atomicInteger);
        log("resultValue2 = "+resultValue2);


        //incrementAndGet 구현
        int resultValue3= incrementAndGet(atomicInteger);
        log("resultValue3 = "+resultValue3);


    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result = false;
        do{
            //현재 값을 읽는다
            getValue = atomicInteger.get();
            log("getValue = "+getValue);
            //다른 스레드가 값을 증가시키지 않았을때만 증가시킴
            result = atomicInteger.compareAndSet(getValue,getValue +1);
            log("result : "+result);
        }
        while(!result);
        return atomicInteger.get();
    }

}
