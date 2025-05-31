package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();    //값을 증가하고 값을 반환
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }
}
