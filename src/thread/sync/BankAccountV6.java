package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount{
    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {  //초기 잔고 -> 계좌 잔고
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {  // synchronized : 한번에 하나의 스레드만 실행 가능. 다른 스레드는 실행 불가. 동기화된 메서드
        log("거래 시작 : "+getClass().getSimpleName());
        try {
            //0.5s 동안 기다리다가 락 획득 못하면
            if(!lock.tryLock(500, TimeUnit.MILLISECONDS)){
                log("[진입 실패] 이미 처리중인 작업이 있습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try{
            //  ==임계 영역 시작==
            log("[검증 시작] 출금액 : "+amount+", 잔액 : "+balance);
            if(balance<amount){
                log("[검증 실패] 출금액 : "+amount+", 잔액 : "+balance);
                return false;
            }
            // 잔고가 출금액 보다 많으면, 진행
            log("[검증 완료] 출금액 : "+amount+", 잔액 : "+balance);
            sleep(1000);    //출금에 걸리는 시간으로 가정
            //잔고가 출금액 보다 많으면, 진행
            balance = balance - amount;
            log("[출금 완료] 출금액 : "+amount+", 잔액 : "+balance);
            //  ==임계 영역 종료==
        }
        finally {
            //exception이 터졌어도 무조건 unlock을 해줘야함. 안그러면 스레드가 waiting 상테에 빠짐
            lock.unlock();
        }
        log("거래 종료 ");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try{
            return balance;
        }
        finally {
            lock.unlock();
        }
    }
}
