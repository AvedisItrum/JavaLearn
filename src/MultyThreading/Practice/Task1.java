package MultyThreading.Practice;

import Practice.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {

    @Test
    public void GenerateAndWriteTest() {
        try {
            Assertions.assertArrayEquals(generateFile(100_000_000), readFile());
        } catch (Exception ex) {

        }
    }

    @Test
    public void outPutTest() {
        try {
            int[] ints = readFile();
            count(ints);
        } catch (Exception e) {
        }
    }

    final String path = "IntArray.txt";

    public void count(int[] ints) throws ExecutionException, InterruptedException {

        List<Callable<String>> tasks = new ArrayList<>();

        ExecutorService eS = Executors.newFixedThreadPool(6);
        List<Future<String>> futures = new ArrayList<>();

        futures.add(eS.submit(() -> "Max: " + Arrays.stream(ints).max().getAsInt() + " from thread" + Thread.currentThread().getName()));
        futures.add(eS.submit(() -> "Min: " + Arrays.stream(ints).min().getAsInt() + " from thread" + Thread.currentThread().getName()));
        futures.add(eS.submit(() -> "Average: " + Arrays.stream(ints).average().getAsDouble() + " from thread" + Thread.currentThread().getName()));
        futures.add(eS.submit(() -> "Evens count: " + Arrays.stream(ints).filter(x -> x % 2 == 0).count() + " from thread" + Thread.currentThread().getName()));
        futures.add(eS.submit(() -> "Odds count: " + Arrays.stream(ints).filter(x -> x % 2 != 0).count() + " from thread" + Thread.currentThread().getName()));
        futures.add(eS.submit(() -> "Sum: " + Arrays.stream(ints).sum() + " from thread" + Thread.currentThread().getName()));

        //Поток - Время
        //  1   - 2.5 сек
        //  2   - 1.8 сек
        //  3   - 1.3 сек
        //  4   - 1.2 сек
        //  5   - 1.0 сек
        //  6   - 1.0 сек


        long time = System.currentTimeMillis();
        for (Future<String> str : futures)
            System.out.println(str.get());
        System.out.println((System.currentTimeMillis() - time) + " millis");
        eS.shutdown();
    }

    public int[] readFile() throws IOException, ClassNotFoundException {
        File file = new File(path);
        FileInputStream iStream = new FileInputStream(file);
        ObjectInputStream objectOS = new ObjectInputStream(iStream);

        return (int[]) objectOS.readObject();
    }

    public int[] generateFile(int size) throws IOException {
        int[] ints = new int[size];
        for (int i = 0; i < size; i++)
            ints[i] = (int) (Math.random() * size);

        File file = new File(path);
        FileOutputStream oStream = new FileOutputStream(file);
        ObjectOutputStream objectOS = new ObjectOutputStream(oStream);

        objectOS.writeObject(ints);

        return ints;
    }
}
