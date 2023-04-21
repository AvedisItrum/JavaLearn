package MultyThreading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class FindPrimeAsync extends Thread {

    public FindPrimeAsync(int till) {
        this.till = till;
    }

    private final int till;
    private volatile LinkedList<Integer> ints;
    private volatile boolean solve = true;

    public int getLength(){
        solve = false;
        int l =ints.size();
        solve = true;
        return l;
    }
    @Override
    public void run() {
        ints = new LinkedList<>();
        ints.add(Integer.valueOf(2));
        ints.add(Integer.valueOf(3));
        int i = 5;
        while (i <= till) {
            i++;
            if (isPrime(i)) {
                while (!solve) {
                }
                ints.add(i);
            }
        }
    }

    public static boolean isPrime(int num) {
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt + 1; i++)
            if (num % i == 0)
                return false;
        return true;
    }

}
