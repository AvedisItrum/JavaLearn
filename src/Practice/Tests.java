package Practice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    public void MyStringBuilderTest(){
        MyStringBuilder sB = new MyStringBuilder("123456789");
        sB.append("1");//1234567891
        sB.append("123");//1234567891123
        sB.Undo();//1234567891
        sB.Undo();//123456789
        sB.ReUndo();//1234567891
        sB.Undo();//123456789
        sB.ReUndo();//1234567891
        sB.ReUndo();//1234567891123
        sB.Remove(3);//1234567891
        sB.Remove(1);//123456789
        sB.Undo();//1234567891
        sB.Undo();//1234567891123
        sB.ReUndo();//1234567891
        sB.ReUndo();//123456789

        assertEquals("123456789",sB.toString());
    }
    @Test
    public void DistinctTest() {
        Integer[] ints = new Integer[]{};
        List<Integer> lI = List.of(0, 1, 2, 2, 3, 3, 3, 4, 4, 5);
        StandAloneMethods.Distinct(lI);
        assertArrayEquals(new Integer[]{1,2,3,4,5},lI.toArray(Integer[]::new));
    }

    public void IteratorTest(){
        Object[] objs = new Object[]{1,2,3,4,"STR"};
        MyIterator mI = new MyIterator(objs);
        while (mI.hasNext())
            System.out.println(mI.next());
    }

    public void MapperTest() {
        Integer[] ints = new Integer[]{0, 1, 2, 2, 3, 3, 3, 4, 4, 5,};
        String str = "";
        StandAloneMethods.mapper(ints).forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
