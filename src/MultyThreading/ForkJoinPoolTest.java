package MultyThreading;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {
    public static void main(String[] args) {
        int[] ints = new int[100000];
        Arrays.fill(ints, 1);

        Example counter = new Example(ints);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int res = forkJoinPool.invoke(counter);
        System.out.println(res);
    }

    static class Example extends RecursiveTask<Integer> {
        int[] array;

        public Example(int[] array) {
            this.array = array;
        }

        @Override
        protected Integer compute() {
            if (array.length <= 2)
                return Arrays.stream(array).sum();
            Example e1 = new Example(Arrays.copyOfRange(array, 0, array.length / 2));
            Example e2 = new Example(Arrays.copyOfRange(array, array.length / 2, array.length));
            e1.fork();
            e2.fork();
            return e1.join() + e2.join();

        }
    }
}
