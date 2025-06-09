package thread.executor.future;

import static thread.util.MyLogger.log;

public class SumTaskMainV1 {
    public static void main(String[] args) throws InterruptedException {
        SumTask task1 =new SumTask(1,50);
        SumTask task2 =new SumTask(51,100);
        Thread t1 = new Thread(task1, "thread-1");
        Thread t2 = new Thread(task2, "thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log(task1.getResult());
        log(task2.getResult());

        int result = task1.getResult()+task2.getResult();
        log("result = "+result);

    }

    static class SumTask implements Runnable{
        int range1;
        int range2;
        int result;
        public SumTask(int range1, int range2) {
            this.range1 = range1;
            this.range2 = range2;
        }

        @Override
        public void run() {
            log("작업 시작");
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int sum = 0;
            for (int i = range1; i <= range2; i++) {
                sum+=i;
            }
            result = sum;
        }

        public int getResult() {
            return result;
        }
    }
}
