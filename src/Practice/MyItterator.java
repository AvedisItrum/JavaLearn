package Practice;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyItterator {

        Object[] data;
        public MyItterator(Object[] objs){
            this.data = objs;
        }

        private int currentIndex = -1;

        int modCount = data.length;


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
