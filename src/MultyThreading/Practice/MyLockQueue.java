package MultyThreading.Practice;

import java.util.concurrent.atomic.AtomicInteger;

public class MyLockQueue {

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

    public MyLockQueue(int SleepTime) {
        SLEEP_TIME = SleepTime;
    }

    /**
     * Lock/Unlock происходит по принципу First In - First Out
     */
    int lock() {
        int index = _threadNumber.getAndIncrement();
        while (_threadIterator.get() != index) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return index;
    }

    void unlock() {
        _threadIterator.incrementAndGet();
    }
}
