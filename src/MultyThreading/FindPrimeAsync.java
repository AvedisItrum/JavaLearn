package MultyThreading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class FindPrimeAsync extends Thread {

    public FindPrimeAsync(int till) {
        this.till = till;
    }

    private final int till;
    volatile LinkedList<Integer> ints;
    volatile boolean solve = true;

    @Override
    public void run() {
        ints = new LinkedList<>();
        ints.add(Integer.valueOf(2));
        ints.add(Integer.valueOf(3));
        int i = 5;
        while (i <= till) {
            if (!solve)
                continue;
            i++;
            if (isPrime(i))
                ints.add(i);
        }
    }

    @Override
    public void interrupt() {
        solve = !solve;
    }

    public static boolean isPrime(int num) {
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt + 1; i++)
            if (num % i == 0)
                return false;
        return true;
    }

}
