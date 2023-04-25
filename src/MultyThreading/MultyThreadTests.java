package MultyThreading;

import jdk.jfr.Timespan;
import org.junit.jupiter.api.Test;

import java.awt.image.FilteredImageSource;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultyThreadTests {


    @Test
    public void task() throws ExecutionException, InterruptedException {
        int l;
        long startTime;
        int repeats = 3;
        List<Long> times = new LinkedList<>();

        for (int i = 0; i < repeats; i++) {
            FindPrimeAsync fPA = new FindPrimeAsync(100_000_000);

            fPA.setName("Thr " + i);

            fPA.start();

            startTime = System.currentTimeMillis();

            while (fPA.isAlive()) {
                fPA.join(5000);
                System.out.println(fPA.getLength() + " elements within " + (System.currentTimeMillis() - startTime) + " milis. Name:" + fPA.getName());
            }
            System.out.println("Done");
            times.add((System.currentTimeMillis() - startTime));

        }

        System.out.println("AVG = " + times.stream().reduce(Long::sum).get() / times.size());

        //ArrayList 9878
        //ArrayList with getLength() 9908

        //LinkedList.add 9906
        //LinkedList.add with getLength() 9951

        //LinkedList.push 9940
        //LinkedList.push with getLength() 9907
    }

    static int simpleTest() {
        int h = 0;
        for (int f = 0; f <= 1_000_000; f++)
            if (f % 2 == 0)
                h = f;

        return h / 100_000;
    }

    @Test
    public void example() {
        int j = 0;
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            j += simpleTest();
        }

        System.out.println("SingleThread: " + (System.currentTimeMillis() - time) + " millis");


        MyThread m = new MyThread();
        m.start();

        if (Math.random() < 0.5)
            m.interrupt();

        try {
            m.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int nbThreads = Thread.getAllStackTraces().keySet().size();
        ExecutorService executorService = Executors.newFixedThreadPool(nbThreads);

        List<Callable<Integer>> tasks = new ArrayList<>(15);

        for (int i = 0; i < 1000; i++) {
            tasks.add(MultyThreadTests::simpleTest);
        }

        List<Future<Integer>> futures = new ArrayList<>();

        time = System.currentTimeMillis();
        try {
            futures = executorService.invokeAll(tasks);
        } catch (Exception ex) {
        }
        System.out.println("MultiThread with " + nbThreads + " threads: " + (System.currentTimeMillis() - time) + " millis");
        Integer sum = 0;

        for (var ints : futures)
            try {
                sum += ints.get();
            } catch (Exception ignored) {
            }

        executorService.shutdown();

        assertEquals(j, sum);
    }
}