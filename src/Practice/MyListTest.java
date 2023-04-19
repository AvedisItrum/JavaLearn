package Practice;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    MyList<Integer> myInts = new MyList<>(new Integer[]{null, 0, 1, 2, 3, 4, null, 5, 6, null, null, null, 7, 8, null, null, null, 9, 10, 11, 12, null, 13, null});

    @Test
    void filterTest() {
        assertArrayEquals(new Integer[]{0, 2, 4, 6, 8, 10, 12}, MyList.filter(myInts, integer -> integer % 2 == 0).toArray());
    }

    @Test
    void testSize() {
        assertEquals(14, myInts.length);
    }

    @Test
    void testIsEmpty() {
        assertFalse(myInts.isEmpty());
    }

    @Test
    void testContains() {
        assertTrue(myInts.contains(1));
    }

    @Test
    void testToArray() {
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, myInts.toArray());
    }

    @Test
    void testToArray1() {
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, myInts.toArray(new Integer[14]));
    }

    @Test
    void testAdd() {
        myInts.add(14);
        assertEquals(14, (int) myInts.get(myInts.length - 1));
    }

    @Test
    void testRemove() {
        myInts.remove(14);
        assertEquals(13, (int) myInts.get(myInts.length - 1));

    }

    @Test
    void testContainsAll() {
        assertTrue(myInts.containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testAddAll() {
        myInts.addAll(Arrays.asList(1, 2, 3));
        assertEquals(16, myInts.lastIndexOf(3));
    }

    @Test
    void testAddAll1() {
        myInts.addAll(2, Arrays.asList(1, 2, 3));
        assertEquals(4, myInts.indexOf(3));
    }

    @Test
    void testRemoveAll() {
        myInts.removeAll(Arrays.asList(1, 2, 3));
        assertArrayEquals(myInts.toArray(), new Integer[]{0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13});
    }

    @Test
    void testRetainAll() {
        myInts.retainAll(List.of(6));
        assertEquals(1, myInts.length);
    }

    @Test
    void testGet() {
        assertEquals(Integer.valueOf(0), myInts.get(0));
    }

    @Test
    void testSet() {
        myInts.set(0, 7);
        assertEquals(Integer.valueOf(7), myInts.get(0));
    }

    @Test
    void testAdd1() {
        myInts.add(0, 0);
        assertEquals(Integer.valueOf(0), myInts.get(0));
        myInts.remove(0);
    }

    @Test
    void testRemove1() {
        myInts.remove(Integer.valueOf(0));
        assertEquals(1, myInts.get(0));
    }

    @Test
    void testIndexOf() {
        myInts.addAll(Arrays.asList(0, 7));
        assertEquals(7, myInts.indexOf(7));
    }

    @Test
    void testLastIndexOf() {
        myInts.addAll(Arrays.asList(0, 7));
        assertEquals(15, myInts.lastIndexOf(7));
    }

    @Test
    void testSubList() {
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5}, myInts.subList(0, 6).toArray());
    }

    @Test
    void testClear() {
        myInts.clear();
        assertEquals(0, myInts.length);
    }
}