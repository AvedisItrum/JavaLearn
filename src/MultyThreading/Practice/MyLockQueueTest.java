package MultyThreading.Practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyLockQueueTest{


    public static void main(String[] args) {
        MyLockQueue lock = new MyLockQueue(10);
        ExecutorService eS = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            eS.submit(() -> {
                lock.lock();
                try {
                    Thread.sleep((int)(Math.random()*250));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Hi from "+ Thread.currentThread().getName());
                lock.unlock();
            });
        }
        eS.close();
    }
}
