package MultyThreading.Practice;

import jdk.jshell.spi.ExecutionControl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyQueueLock implements Lock {
    /**
     * Время, в млс, на которое поток будет засыпать
     */
    final int SLEEP_TIME;

    /**
     * Переменная, указывающая, какой поток должен проснуться
     */
    private final AtomicInteger _threadIterator = new AtomicInteger(0);

    /**
     * Переменная, указывающая, какой индекс будет у следующего потока
     */
    private final AtomicInteger _threadNumber = new AtomicInteger(0);

    public MyQueueLock(int SleepTime) {
        SLEEP_TIME = SleepTime;
    }

    /**
     * Lock/Unlock происходит по принципу First In - First Out
     */
    @Override
    public void lock() {
        int index = _threadNumber.getAndIncrement();
        while (_threadIterator.get() != index) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                _threadNumber.decrementAndGet();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int index = _threadNumber.getAndIncrement();
        while (_threadIterator.get() != index) {
            if (Thread.interrupted()) {
                _threadNumber.decrementAndGet();
                throw new InterruptedException();
            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                _threadNumber.decrementAndGet();
                throw new InterruptedException();
            }
        }
    }

    @Override
    public boolean tryLock() {
        if (_threadNumber.get() == _threadIterator.get()) {
            _threadNumber.incrementAndGet();
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        time = unit.toMillis(time);
        long startTime = System.currentTimeMillis();
        _threadNumber.incrementAndGet();

        while (System.currentTimeMillis()-startTime<time)
        {
            if (_threadNumber.get() == _threadIterator.get()) {
                return true;
            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                _threadNumber.decrementAndGet();
                throw new InterruptedException();
            }
        }
        _threadNumber.decrementAndGet();
        return false;
    }

    @Override
    public void unlock() {
        _threadIterator.incrementAndGet();
    }

    @Override
    public Condition newCondition() {
        try {
            throw new ExecutionControl.NotImplementedException("Not Implemented");
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }

    }
}
