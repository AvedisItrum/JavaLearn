package Practice.MyImplementations;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class MyList<T> implements List<T> {
    Object[] data;
    int size = 0;
    int GROW_STEP = 10;
    static final int DEFAULT_INITIAL_SIZE = 10;


    public MyList(T[] t) {
        data = t;
        size = data.length;
        multiShrinkArray(0);
    }

    public MyList(int initialSize, int sizeStep) {
        this.GROW_STEP = sizeStep;
        data = new Object[initialSize];
    }

    public MyList(int initialSize) {
        data = new Object[initialSize];
    }

    public MyList() {
        data = new Object[DEFAULT_INITIAL_SIZE];
    }

    public static <T> Collection<T> filter(Collection<T> col, Predicate<T> pr) {
        return col.stream().filter(pr).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object t : data)
            if (t.equals(o))
                return true;
        return false;
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(Arrays.stream(data).filter(Objects::nonNull).toArray(), size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < data.length)
            return (T1[]) Arrays.copyOf(data, size);
        System.arraycopy(data, 0, a, 0, size);
        for (int i = data.length; i < a.length; i++)
            a[i] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        if (size == data.length) {
            expandArray(GROW_STEP);
        }
        data[size] = t;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(data[i])) {
                singleShrinkArray(i);
                size--;
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
        if (c.size() > data.length - size) {
            expandArray(c.size() - (data.length - size) + GROW_STEP);
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
    private boolean expandArray(int count) {
        if (count < 1)
            return false;
        T[] array = (T[]) new Object[data.length + count];
        System.arraycopy(data, 0, array, 0, data.length);
        data = array;
        return true;
    }

    public void singleShrinkArray(int index) {
        System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
        trimToSize(GROW_STEP);
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


        trimToSize(GROW_STEP);
    }

    public void trimToSize(int cellsCountToLeave) {
        for (int i = 0; i < data.length; i++)
            if (data[i] == null) {
                size = i;
                break;
            }
        if (data.length - size < cellsCountToLeave) {
            expandArray(cellsCountToLeave - (data.length - size));
            return;
        }
        T[] t = (T[]) new Object[size + cellsCountToLeave];
        System.arraycopy(data, 0, t, 0, size);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size)
            index = size;

        if (index + c.size() >= data.length)
            expandArray(index + c.size() - data.length + GROW_STEP);

        System.arraycopy(data, index, data, index + c.size(), size - index);

        System.arraycopy(c.toArray(), 0, data, index, c.size());
        size += c.size();
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
                size--;
            }
        multiShrinkArray(first);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        for (int i = 0; i < data.length; i++)
            if (data[i] != null && !c.contains(data[i])) {
                size--;
                data[i] = null;
            }

        multiShrinkArray(0);
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
        trimToSize(GROW_STEP);
    }

    @SuppressWarnings("unchecked")
    private T dataToValue(Object o){
        return (T)o;
    }

    @Override
    public T get(int index) {
        return dataToValue(data[index]);
    }

    @Override
    public T set(int index, T element) {
        if (index >= data.length)
            expandArray(index - (data.length - 1) + GROW_STEP);
        data[index] = element;
        return dataToValue(data[index]);
    }

    @Override
    public void add(int index, T element) {
        if (data.length == size)
            expandArray(GROW_STEP);
        if (index >= size)
            index = size;
        //!!!!!!!!
        else
            System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= data.length)
            return null;
        T t = dataToValue(data[index]);
        data[index] = null;
        size--;
        singleShrinkArray(index);
        return t;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--)
            if (data[i].equals(o))
                return i;
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<Object> t = new ArrayList<>(toIndex - fromIndex);
        t.addAll(Arrays.asList(data).subList(fromIndex, toIndex));
        return (List<T>) t;
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
                return dataToValue(data[++currentIndex]);
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
                return dataToValue(data[++currentIndex]);
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
                return dataToValue(data[--currentIndex]);
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
