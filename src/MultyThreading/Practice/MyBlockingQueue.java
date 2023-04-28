package MultyThreading.Practice;

import jdk.jshell.spi.ExecutionControl;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyBlockingQueue<T> extends AbstractQueue<T> {

    //To queue
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    volatile LinkedList<T> list = new LinkedList<>();

    @Override
    public Iterator<T> iterator() {
        return list.listIterator();
    }

    @Override
    public int size() {
        lock.writeLock().lock();
        int size = list.size();
        lock.writeLock().unlock();
        return size;
    }


    @Override
    public boolean offer(T t) {
        lock.readLock().lock();
        lock.writeLock().lock();
        list.addLast(t);
        lock.writeLock().unlock();
        lock.readLock().unlock();
        return true;
    }

    @Override
    public T poll() {
        lock.readLock().lock();
        lock.writeLock().lock();
        T t = list.poll();
        lock.writeLock().unlock();
        lock.readLock().unlock();
        return t;
    }

    @Override
    public T peek() {
        lock.writeLock().lock();
        T t = list.peek();
        lock.writeLock().unlock();
        return t;
    }
}
