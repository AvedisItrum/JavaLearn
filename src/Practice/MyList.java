package Practice;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Filter;


public class MyList<T> implements List<T> {
    T[] data;
    int length = 0;
    int sizeStep = 10;
    int initialSize = 10;

    public MyList(T[] t) {
        data = t;
        length = data.length;
        multiShrinkArray(0);

    }

    public MyList(int initialSize, int sizeStep) {
        if (initialSize != -1)
            this.initialSize = initialSize;
        if (sizeStep != -1)
            this.sizeStep = sizeStep;

        data = (T[]) new Object[this.initialSize];

    }

    public MyList(int initialSize) {
        this(initialSize, -1);
    }

    public MyList() {
        this(-1, -1);
    }

    public static<T> Collection<T> filter (Collection<T> col, Predicate<T> pr){
       return col.stream().filter(pr).toList();
    }

    @Override
    public int size() {
        return length < 0 ? Integer.MAX_VALUE : length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : data)
            if (t.equals(o))
                return true;
        return false;
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(Arrays.stream(data).filter(x -> x != null).toArray(), length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < data.length)
            return (T1[]) Arrays.copyOf(data, length);
        System.arraycopy(data, 0, a, 0, length);
        for (int i = data.length; i < a.length; i++)
            a[i] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        if (length == data.length) {
            expandArray(sizeStep);
        }
        data[length] = t;
        length++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++)
            if (o.equals(data[i])) {
                singleShrinkArray(i);
                length--;
                return true;
            }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var t : c)
            if (!contains(t)) {
                return false;
            }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() > data.length - length) {
            expandArray(c.size() - (data.length - length) + sizeStep);
        }
        for (T t : c)
            add(t);
        return true;
    }

    /**
     * Добавляет в конце "{@code count}" ячеек
     *
     * @param count Кол-во ячеек для дополнения
     * @return
     */
    public boolean expandArray(int count) {
        if (count < 1)
            return false;
        T[] array = (T[]) new Object[data.length + count];
        System.arraycopy(data, 0, array, 0, data.length);
        data = array;
        return true;
    }

    public void singleShrinkArray(int index) {
        System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
        trimToSize(sizeStep);
    }

    /**
     * Смешает все объекты влево.
     * <p>[1,2,null,3,null,4] -> [1,2,3,4,null,null]
     *
     * @param from Индекс, с которого нужно начать
     */
    public void multiShrinkArray(int from) {
        int lastValueIndex = 0;
        for (int i = from; i < data.length; i++) {
            if (data[i] != null)
                continue;
            for (int j = Math.max(i, lastValueIndex); j < data.length; j++) {
                if (data[j] == null)
                    continue;
                lastValueIndex = j + 1;
                data[i] = data[j];
                data[j] = null;
                break;

            }
        }


        trimToSize(sizeStep);
    }

    public void trimToSize(int cellsCountToLeave) {
        for (int i = 0; i < data.length; i++)
            if (data[i] == null) {
                length = i;
                break;
            }
        if (data.length - length < cellsCountToLeave) {
            expandArray(cellsCountToLeave - (data.length - length));
            return;
        }
        T[] t = (T[]) new Object[length + cellsCountToLeave];
        System.arraycopy(data, 0, t, 0, length);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > length)
            index = length;

        if (index + c.size() >= data.length)
            expandArray(index + c.size() - data.length + sizeStep);

        System.arraycopy(data, index, data, index + c.size(), length - index);

        System.arraycopy(c.toArray(), 0, data, index, c.size());
        length += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int first = 0;
        for (int i = 0; i < data.length; i++)
            if (c.contains(data[i])) {
                if (first == 0)
                    first = i;
                data[i] = null;
                length--;
            }
        multiShrinkArray(first);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        for (int i = 0; i < data.length; i++)
            if (data[i] != null && !c.contains(data[i])) {
                length--;
                data[i] = null;
            }

        multiShrinkArray(0);
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            data[i] = null;
        }
        length = 0;
        trimToSize(sizeStep);
    }

    @Override
    public T get(int index) {
        return data[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= data.length)
            expandArray(index - (data.length - 1) + sizeStep);
        data[index] = element;
        return data[index];
    }

    @Override
    public void add(int index, T element) {
        if (data.length == length)
            expandArray(sizeStep);
        if (index >= length)
            index = length;
        else
            System.arraycopy(data, index, data, index + 1, length - index);

        data[index] = element;
        length++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= data.length)
            return null;
        T t = data[index];
        data[index] = null;
        length--;
        singleShrinkArray(index);
        return t;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++)
            if (data[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--)
            if (data[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> t = new ArrayList<>(toIndex - fromIndex);
        t.addAll(Arrays.asList(data).subList(fromIndex, toIndex));
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int currentIndex = -1;

            int modCount = data.length;

            @Override
            public boolean hasNext() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                return currentIndex + 1 < data.length && data[currentIndex + 1] != null;
            }

            @Override
            public T next() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                return data[++currentIndex];
            }

            @Override
            public void remove() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                MyList.this.remove(currentIndex);
                modCount--;
            }
        };
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(-1);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            int currentIndex = index;

            int modCount = data.length;

            boolean canModify = true;

            @Override
            public boolean hasNext() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                return currentIndex + 1 < data.length && data[currentIndex + 1] != null;
            }

            @Override
            public T next() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                canModify = true;
                return data[++currentIndex];
            }

            @Override
            public boolean hasPrevious() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                return currentIndex - 1 > -1 && data[currentIndex - 1] != null;
            }

            @Override
            public T previous() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                canModify = true;
                return data[--currentIndex];
            }

            @Override
            public int nextIndex() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                return currentIndex == data.length - 1 ? data.length : currentIndex + 1;
            }

            @Override
            public int previousIndex() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                return currentIndex == 0 ? -1 : currentIndex - 1;
            }

            @Override
            public void remove() {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                if (canModify) {
                    MyList.this.remove(currentIndex);
                    canModify = false;
                    modCount--;
                }
            }

            @Override
            public void set(T t) {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                if (canModify)
                    data[currentIndex] = t;
            }

            @Override
            public void add(T t) {
                if (modCount != data.length)
                    throw new ConcurrentModificationException();
                canModify = false;
                MyList.this.add(currentIndex, t);
                modCount++;
            }
        };
    }
}
