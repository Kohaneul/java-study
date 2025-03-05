package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static thread.util.MyLogger.log;

public class MyPrinter {
    public static void main(String[] args) {
        Printer p1  = new Printer();
        Thread t1 = new Thread(p1,"t1");
        t1.start();
        Scanner sc = new Scanner(System.in);
        while(true){
            log("프린터할 문서를 입력하세요 .  종료(q)");

            String input = sc.nextLine();
            if(input.equals("q")){
                log("프린터 종료");
                t1.interrupt();
                break;
            }
            p1.addJob(input);
        }
        }



    static class Printer implements Runnable{
        Queue<String> addJob = new ConcurrentLinkedQueue<>();
        @Override
        public void run() {
            while(!Thread.interrupted()){
                    if(addJob.isEmpty()){
                        Thread.yield();
                        continue;
                }
                try {
                    String job = addJob.poll();
                    log("출력시작 : "+job +" 대기문서 : "+addJob);
                    log("출력 완료");

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log("인터럽트 !");
                    break;
                }
            }
        }

        public void addJob(String input){
            addJob.offer(input);
        }
    }
}
