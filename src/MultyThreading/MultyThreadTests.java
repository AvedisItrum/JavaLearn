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
import java.util.logging.Filter;

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
}
