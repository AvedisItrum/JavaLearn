package Practice;

import java.util.*;

public class Tests {
    public static void main(String[] args) {
        System.out.println("Mapper:");
        MapperTest();
        System.out.println("Distinct:");
        DistinctTest();
        System.out.println("MyList:");
        MyListTest();
    }

    public static void MyListTest() {
        MyList<Integer> myInts = new MyList<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            if (random.nextBoolean())
                myInts.add(i, i);
            else
                myInts.remove(myInts.size() - 1);

        }
        myInts.remove(2);


        MyList<Integer> myInts2 = new MyList<>(new Integer[]{null, 0, 1, 2, 3, 4, null, 5, 6, null, null, null, 7, 8, null, null, null, 9, 10, 11, 12, null, 13, null});
        myInts2.multiShrinkArray(0);

        ListIterator itter = myInts2.listIterator();

        while (itter.hasNext())
            System.out.println(itter.next());
    }

    public static void DistinctTest() {
        Integer[] ints = new Integer[]{0, 1, 2, 2, 3, 3, 3, 4, 4, 5,};
        List<Integer> lI = Arrays.stream(ints).toList();

        StandAloneMethods.Distinct(lI).stream().forEach(System.out::println);
    }

    public static void MapperTest() {
        Integer[] ints = new Integer[]{0, 1, 2, 2, 3, 3, 3, 4, 4, 5,};

        StandAloneMethods.mapper(ints).forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
