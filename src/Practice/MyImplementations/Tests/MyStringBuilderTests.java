package Practice.MyImplementations.Tests;

import Practice.MyImplementations.MyStringBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStringBuilderTests {
    MyStringBuilder sB = new MyStringBuilder("123456789");

    @Test
    public void AppendCharTest(){
        sB.append('C');
        assertEquals("123456789C",sB.toString());
    }
    @Test
    public void AppendStringTest(){
        sB.append("123321");
        assertEquals("123456789123321",sB.toString());
    }
    @Test
    public void AppendSubStringTest(){
        sB.append("12321",1,4);
        assertEquals("123456789232",sB.toString());
    }
    @Test
    public void RemoveTest(){
        sB.remove();
        assertEquals("12345678",sB.toString());
    }
    @Test
    public void Remove5Test(){
        sB.remove(5);
        assertEquals("1234",sB.toString());
    }
    @Test
    public void RemoveAboveLengthTest(){
        sB.remove(20);
        assertEquals("",sB.toString());
    }
    @Test
    public void SubSequenceTest(){
        assertEquals("2345",sB.subSequence(1,5));
    }
    @Test
    public void UndoTest(){
        sB.remove(2);
        sB.undo();
        assertEquals("123456789",sB.toString());
    }
    @Test
    public void MultipleUndoTest(){
        sB.remove(2);
        sB.remove(2);
        sB.remove(2);
        sB.undo();
        sB.undo();
        sB.undo();
        assertEquals("123456789",sB.toString());
    }@Test
    public void UndoAboveAmountTest(){
        sB.remove(2);
        sB.undo();
        sB.undo();
        sB.undo();
        assertEquals("123456789",sB.toString());
    }
    @Test
    public void ReUndoTest(){
        sB.remove(2);
        sB.undo();
        sB.reUndo();
        assertEquals("1234567",sB.toString());
    }
    @Test
    public void MultipleReUndoTest(){
        sB.remove(2);
        sB.remove(2);
        sB.remove(2);
        sB.undo(3);
        sB.reUndo(3);
        assertEquals("123",sB.toString());
    }@Test
    public void ReUndoAboveAmountTest(){
        sB.remove(2);
        sB.undo(3);
        sB.reUndo(5);
        assertEquals("1234567",sB.toString());
    }
}
