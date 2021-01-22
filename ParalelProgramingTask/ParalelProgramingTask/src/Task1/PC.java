package Task1;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PC {
    private ReentrantLock locker = new ReentrantLock();
    private final Condition bufferNotFull = locker.newCondition();
    private final Condition bufferNotEmpty = locker.newCondition();
    private LinkedList<Integer> list = new LinkedList<>();
    int capacity = 10;
    int value = 0;

    public void produce() throws InterruptedException {
        locker.lock();
        while (list.size() == capacity){
            System.out.println(Thread.currentThread().getName() +
                    "Buffer is full, waiting");
            bufferNotEmpty.await();
        }
        System.out.println("Producer produced: " + value);
        list.add(value++);
        bufferNotFull.signalAll();
        locker.unlock();
    }

    public void consume() throws InterruptedException {
        locker.lock();
        while (list.isEmpty()) {
            System.out.println(Thread.currentThread().getName()
                    + " : Buffer is empty, waiting");
            bufferNotFull.await();
        }
        int val = list.removeFirst();
        System.out.println("Consumer consumed: " + val);
        bufferNotEmpty.signalAll();
        locker.unlock();
    }
}
