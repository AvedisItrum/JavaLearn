package Practice;

import Practice.MyImplementations.MyIterator;
import Practice.MyImplementations.MyStringBuilder;
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
        sB.undo();//1234567891
        sB.undo();//123456789
        sB.reUndo();//1234567891
        sB.undo();//123456789
        sB.reUndo();//1234567891
        sB.reUndo();//1234567891123
        sB.remove(3);//1234567891
        sB.remove(1);//123456789
        sB.undo();//1234567891
        sB.undo();//1234567891123
        sB.undo();
        sB.undo();
        sB.undo();
        sB.undo();
        sB.undo();
        sB.undo();
        sB.undo();
        sB.undo();
        sB.remove(1);
        sB.reUndo();
        sB.reUndo();
        sB.reUndo();
        sB.reUndo();
        sB.reUndo();
        sB.reUndo();
        sB.remove(1);
        sB.reUndo();
        sB.reUndo();
        sB.reUndo();


        assertEquals("1234567",sB.toString());
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
