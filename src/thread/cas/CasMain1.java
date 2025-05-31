package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMain1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger =  new AtomicInteger();
        System.out.println("start value = "+atomicInteger.get());
        //  값을 비교하고 같으면 셋팅해라
        boolean result1 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result1 = "+result1+", value = "+atomicInteger.get());

        //위에 있는 atomicInteger.get()와 값 비교
        boolean result2 = atomicInteger.compareAndSet(0, 200
        );
        System.out.println("result2 = "+result2+", value = "+atomicInteger.get());
    }
}
