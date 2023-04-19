package Practice;

import java.util.ConcurrentModificationException;

public class MyIterator {

    Object[] data;

    public MyIterator(Object[] objs) {
        this.data = objs;
        modCount = data.length;
    }

    private int currentIndex = -1;

    int modCount;


    public boolean hasNext() {
        if (modCount != data.length)
            throw new ConcurrentModificationException();
        return currentIndex + 1 < data.length && data[currentIndex + 1] != null;
    }


    public Object next() {
        if (modCount != data.length)
            throw new ConcurrentModificationException();
        return data[++currentIndex];
    }

}
