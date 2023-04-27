package MultyThreading.Practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyReadWriteLockTest {
    public static void main(String[] args) {
        MyReadWriteLock mRWL = new MyReadWriteLock();

        mRWL.write("Hello my MRWL");

        ExecutorService eS = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            if (Math.random() > 0.5)
                eS.submit(() -> {
                    System.out.println("Started reading in " + Thread.currentThread().getName());
                    String str = mRWL.read();
                    System.out.println("Read " + str + " in " + Thread.currentThread().getName());
                });
            else
                eS.submit(() -> {
                    System.out.println("Started writing in " + Thread.currentThread().getName());
                    mRWL.write(Thread.currentThread().getName());
                    System.out.println("Wrote " + Thread.currentThread().getName());
                });
        }
        eS.close();
    }
}
