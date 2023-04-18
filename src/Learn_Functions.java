import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Learn_Functions {
    public static void main(String[] args) {
        ParallelStream();
        SequentialStream();
    }

    public static void SequentialStream() {
        IntStream.range(0,10).forEach(x -> System.out.println("S " + x + " : " + Thread.currentThread().getName()));
    }

    public static void ParallelStream() {
        IntStream.range(0,10).parallel().forEach(x -> System.out.println("P " + x + " : " + Thread.currentThread().getName()));
    }

    /**
     * Генерирует случайный числа в диапозоне (From <= x <= To) в кол-ве Amount штук
     */
    static List<Integer> ShowAndReturnRandomEvensInRange(int From, int To, int Amount) {
        return Stream.generate(() -> (From + Math.random() * (To - From)))//Supplier
                .map(x -> Math.round(x.floatValue()))//Function
                .filter(x -> x % 2 == 0)//Predicate
                .limit(Amount)
                .peek(System.out::println)//Consumer
                .collect(Collectors.toList());
    }

    static List<Integer> ShowAndReturnEvensInRow(int From, int To) {
        if (From % 2 != 0)
            From++;
        return Stream.iterate(From, x -> x < To, x -> x += 2)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public static void Evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer val : list) {
            if (predicate.test(val))
                System.out.println(val);
        }
    }
}
