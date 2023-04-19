package Practice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static void main(String[] args){
        IteratorTest();
    }
    @Test
    public static void DistinctTest() {
        Integer[] ints = new Integer[]{};
        List<Integer> lI = List.of(0, 1, 2, 2, 3, 3, 3, 4, 4, 5);
        StandAloneMethods.Distinct(lI);
        assertArrayEquals(new Integer[]{1,2,3,4,5},lI.toArray(Integer[]::new));
    }

    public static void IteratorTest(){
        Object[] objs = new Object[]{1,2,3,4,"STR"};
        MyIterator mI = new MyIterator(objs);
        while (mI.hasNext())
            System.out.println(mI.next());
    }

    public static void MapperTest() {
        Integer[] ints = new Integer[]{0, 1, 2, 2, 3, 3, 3, 4, 4, 5,};
        String str = "";
        StandAloneMethods.mapper(ints).forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
