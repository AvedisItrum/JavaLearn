package MultyThreading;

import jdk.jfr.Timespan;
import org.junit.jupiter.api.Test;

import java.awt.image.FilteredImageSource;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Filter;

public class MultyThreadTests {

    @Test
    public void task() throws ExecutionException, InterruptedException {
        int l;
        long startTime;

        FindPrimeAsync fPA = new FindPrimeAsync(5_000_000);
        fPA.start();

        startTime = System.currentTimeMillis();
        while (fPA.isAlive()) {
            fPA.join(500);

            fPA.interrupt();
            l = fPA.ints.size();
            fPA.interrupt();

            System.out.println(l + " elements within " + (System.currentTimeMillis() - startTime) + " milis");
        }
    }
}
